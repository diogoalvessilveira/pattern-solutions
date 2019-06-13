package br.com.diogo.pattern.solutions;

import br.com.diogo.pattern.solutions.stragegy.Strategy;

@Strategy(tipoEstrategia = EstrategiaTeste.class, regras = "TESTE")
public class TestandoA implements EstrategiaTeste {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void teste() {
		System.out.println("Executou A");
	}

}
