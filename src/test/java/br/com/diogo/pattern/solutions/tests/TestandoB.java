package br.com.diogo.pattern.solutions.tests;

import br.com.diogo.pattern.solutions.stragegy.Strategy;
import br.com.diogo.pattern.solutions.strategy.tests.EstrategiaTeste;

@Strategy(tipoEstrategia = EstrategiaTeste.class, regras = { "TESTE1", "TESTE2" })
public class TestandoB implements EstrategiaTeste {

	private String codigo;

	@Override
	public String teste() {
		return "Executou B";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
