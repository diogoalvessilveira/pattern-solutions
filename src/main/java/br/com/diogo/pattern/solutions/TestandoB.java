package br.com.diogo.pattern.solutions;

import br.com.diogo.pattern.solutions.stragegy.Strategy;

@Strategy(tipoEstrategia = EstrategiaTeste.class, eventos = "TESTE2")
public class TestandoB implements EstrategiaTeste {

	@Override
	public void teste() {
		System.out.println("Executou B");
	}

}
