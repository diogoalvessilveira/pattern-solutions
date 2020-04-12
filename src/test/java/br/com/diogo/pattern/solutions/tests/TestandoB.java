package br.com.diogo.pattern.solutions.tests;

import br.com.diogo.pattern.solutions.stragegy.Strategy;
import br.com.diogo.pattern.solutions.strategy.tests.EstrategiaTeste;

@Strategy(tipoEstrategia = EstrategiaTeste.class, regras = "TESTE2")
public class TestandoB implements EstrategiaTeste {

	@Override
	public String teste() {
		return "Executou B";
	}

}
