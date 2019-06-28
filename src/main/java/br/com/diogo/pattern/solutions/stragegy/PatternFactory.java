package br.com.diogo.pattern.solutions.stragegy;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
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

import br.com.diogo.pattern.solutions.observer.ObserverPattern;

@Repository
public class PatternFactory implements IStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(PatternFactory.class);
	private @Autowired ApplicationContext applicationContext;

	private Map<Class<?>, List<Object>> tiposAnotados = new HashMap<>();
	private Map<Class<?>, Strategy> strategyCache = new HashMap<>();
	private Map<Class<?>, ObserverPattern> observerCache = new HashMap<>();
	private Map<Class<? extends Observable>, List<Object>> observersAnotados = new HashMap<>();

	@PostConstruct
	public void init() {
		strategy();
	}

	private void strategy() {
		Collection<Object> colecao = getColecaoBeansAnotados(Strategy.class);
		verificarEstrategiasAnotadas(colecao);

		for (Object bean : colecao) {
			Strategy strategy = strategyCache.get(bean.getClass());
			getBeansComMesmoTipoDeEstrategia(strategy).add(bean);
		}
	}

	private void observer() {
		Collection<Object> colecao = getColecaoBeansAnotados(ObserverPattern.class);
		// TODO verificarEstrategiasAnotadas(colecao);

		for (Object bean : colecao) {
			ObserverPattern observer = observerCache.get(bean.getClass());
			// TODO getBeansComMesmoTipoDeEstrategia(strategy).add(bean);
		}
	}

	private Collection<Object> getColecaoBeansAnotados(Class<? extends Annotation> classeAnotacao) {
		Map<String, Object> beansAnotados = applicationContext.getBeansWithAnnotation(classeAnotacao);
		return beansAnotados.values();
	}

	private void verificarEstrategiasAnotadas(Collection<Object> beansAnotados) {
		Set<String> estrategiasUtilizadas = new HashSet<>();

		if (Objects.nonNull(beansAnotados)) {
			beansAnotados.forEach(bean -> {
				Strategy strategy = AnnotationUtils.findAnnotation(bean.getClass(), Strategy.class);

				if (Objects.isNull(strategy)) {
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

				if (Objects.nonNull(strategy.regras())) {
					for (String string : strategy.regras()) {
						adicionarCasoNaoExista(strategy.tipoEstrategia(), string, estrategiasUtilizadas);
					}
				}
			});
		}
	}

	private boolean isDefault(Strategy strategy) {
		return strategy.regras().length == 0;
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
	public <T> T getStrategy(Class<T> interfaceUtilizadaComoEstrategia, String regraAtual) {
		List<Object> beansAssociadosInterface = tiposAnotados.get(interfaceUtilizadaComoEstrategia);
		Assert.notEmpty(beansAssociadosInterface, "Nenhuma estratégia encontrada para a classe'"
				+ interfaceUtilizadaComoEstrategia.getName() + "', săo as estratégias marcadas com @Strategy");

		Object regraStrategy = obterEstrategiaDaRegra(beansAssociadosInterface, regraAtual);
		if (regraStrategy == null) {
			throw new RuntimeException(
					"Nenhuma estratégia encontrada para a classe '" + interfaceUtilizadaComoEstrategia + "'");
		}
		return (T) regraStrategy;
	}

	private Object obterEstrategiaDaRegra(List<Object> beansAssociadosInterface, String regraAtual) {
		Object defaultStrategy = null;
		for (Object bean : beansAssociadosInterface) {
			Strategy strategy = strategyCache.get(bean.getClass());
			if (Objects.nonNull(regraAtual)) {
				for (String regra : strategy.regras()) {
					if (regra.equalsIgnoreCase(regraAtual)) {
						LOG.debug("Encontrada a estratégia para o tipo '" + strategy.tipoEstrategia() + "' regra = '"
								+ regraAtual + "'");
						return bean;
					}
				}
			}

			if (isDefault(strategy)) {
				defaultStrategy = bean;
				if (Objects.isNull(regraAtual)) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Nenhuma regra selecionada, retornando a estratégia default.");
					}
					return defaultStrategy;
				}
			}
		}
		if (LOG.isDebugEnabled()) {
			if (defaultStrategy != null) {
				LOG.debug("Nenhuma regra específico encontrada, retornando a estratégia default.");
			}
		}
		return defaultStrategy;
	}
}
