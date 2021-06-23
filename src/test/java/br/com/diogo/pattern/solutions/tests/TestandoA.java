package br.com.diogo.pattern.solutions.tests;

import br.com.diogo.pattern.solutions.composite.Composite;
import br.com.diogo.pattern.solutions.stragegy.Strategy;
import br.com.diogo.pattern.solutions.strategy.tests.EstrategiaTeste;

@Strategy(tipoEstrategia = EstrategiaTeste.class, regras = { "A", "B", "C" })
public class TestandoA extends Composite<TestandoA> implements EstrategiaTeste {

	private String nome;
	private String sobrenome;

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String teste() {
		return "Executou A";
	}

}
