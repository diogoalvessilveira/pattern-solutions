package br.com.diogo.pattern.solutions;

import br.com.diogo.pattern.solutions.stragegy.Strategy;

@Strategy(tipoEstrategia = EstrategiaTeste.class, eventos = "TESTE")
public class TestandoA implements EstrategiaTeste {

	@Override
	public void teste() {
		System.out.println("Executou A");
	}

}
