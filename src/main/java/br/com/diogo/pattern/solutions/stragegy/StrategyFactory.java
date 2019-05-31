package br.com.diogo.pattern.solutions.stragegy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import br.com.diogo.pattern.solutions.Utils;

@Repository
public class StrategyFactory implements IStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(StrategyFactory.class);
	private @Autowired ApplicationContext applicationContext;

	private Map<Class<?>, List<Object>> tiposAnotados = new HashMap<>();
	private Map<Class<?>, Strategy> strategyCache = new HashMap<>();

	@PostConstruct
	public void init() {
		Map<String, Object> beansAnotados = applicationContext.getBeansWithAnnotation(Strategy.class);
		Collection<Object> colecao = beansAnotados.values();
		verificarEstrategiasAnotadas(colecao);

		for (Object bean : colecao) {
			Strategy strategy = strategyCache.get(bean.getClass());
			getBeansComMesmoTipoDeEstrategia(strategy).add(bean);
		}
	}

	private void verificarEstrategiasAnotadas(Collection<Object> beansAnotados) {
		Set<String> estrategiasUtilizadas = new HashSet<>();

		if (!Utils.nuloOuVazio(beansAnotados)) {
			beansAnotados.forEach(bean -> {
				Strategy strategy = AnnotationUtils.findAnnotation(bean.getClass(), Strategy.class);

				if (Utils.nuloOuVazio(strategy)) {
					try {
						Object target = ((Advised) bean).getTargetSource().getTarget();
						strategy = AnnotationUtils.findAnnotation(target.getClass(), Strategy.class);
					} catch (Exception e) {
						LOG.error("Năo foi possível utilizar a estratégia com anotaçăo para o bean!", e);
					}
				}

				strategyCache.put(bean.getClass(), strategy);

				if (isDefault(strategy)) {
					adicionarCasoNaoExista(strategy.tipoEstrategia(), "default", estrategiasUtilizadas);
				}

				if (!Utils.nuloOuVazio(strategy.eventos())) {
					for (String string : strategy.eventos()) {
						adicionarCasoNaoExista(strategy.tipoEstrategia(), string, estrategiasUtilizadas);
					}
				}
			});
		}
	}

	private boolean isDefault(Strategy strategy) {
		return strategy.eventos().length == 0;
	}

	private String criarChave(Class<?> classe, String nomeEnum) {
		return (classe + "_" + nomeEnum).toLowerCase();
	}

	private void adicionarCasoNaoExista(Class<?> classeEstrategia, String nomeEnum, Set<String> estrategiasUtilizadas) {
		if (estrategiasUtilizadas.contains(criarChave(classeEstrategia, nomeEnum))) {
			throw new RuntimeException(
					"Năo é possível aplicar somente uma estratégia, pois foi identificado que há multiplas estratégias para a classe '"
							+ classeEstrategia + "' e Enum '" + nomeEnum + "'");
		}
		estrategiasUtilizadas.add(criarChave(classeEstrategia, nomeEnum));
	}

	private List<Object> getBeansComMesmoTipoDeEstrategia(Strategy strategy) {
		List<Object> beansComMesmoTipoDeEstrategia = tiposAnotados.get(strategy.tipoEstrategia());
		if (beansComMesmoTipoDeEstrategia != null) {
			return beansComMesmoTipoDeEstrategia;
		} else {
			List<Object> novaListaBeans = new ArrayList<>();
			tiposAnotados.put(strategy.tipoEstrategia(), novaListaBeans);
			return novaListaBeans;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getStrategy(Class<T> interfaceUtilizadaComoEstrategia, String enumAtual) {
		List<Object> beansAssociadosInterface = tiposAnotados.get(interfaceUtilizadaComoEstrategia);
		Assert.notEmpty(beansAssociadosInterface, "Nenhuma estratégia encontrada para a classe'"
				+ interfaceUtilizadaComoEstrategia.getName() + "', săo as estratégias marcadas com @Strategy");

		Object eventoStrategy = obterEstrategiaDoEvento(beansAssociadosInterface, enumAtual);
		if (eventoStrategy == null) {
			throw new RuntimeException(
					"Nenhuma estratégia encontrada para a classe '" + interfaceUtilizadaComoEstrategia + "'");
		}
		return (T) eventoStrategy;
	}

	private Object obterEstrategiaDoEvento(List<Object> beansAssociadosInterface, String enumAtual) {
		Object defaultStrategy = null;
		for (Object bean : beansAssociadosInterface) {
			Strategy strategy = strategyCache.get(bean.getClass());
			if (!Utils.nuloOuVazio(enumAtual)) {
				for (String evento : strategy.eventos()) {
					if (evento.equalsIgnoreCase(enumAtual)) {
						LOG.debug("Encontrada a estratégia para o tipo '" + strategy.tipoEstrategia() + "' enum = '"
								+ enumAtual + "'");
						return bean;
					}
				}
			}

			if (isDefault(strategy)) {
				defaultStrategy = bean;
				if (Utils.nuloOuVazio(enumAtual)) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Nenhum enum selecionado, retornando a estratégia default.");
					}
					return defaultStrategy;
				}
			}
		}
		if (LOG.isDebugEnabled()) {
			if (defaultStrategy != null) {
				LOG.debug("Nenhum enum específico encontrado, retornando a estratégia default.");
			}
		}
		return defaultStrategy;
	}
}
