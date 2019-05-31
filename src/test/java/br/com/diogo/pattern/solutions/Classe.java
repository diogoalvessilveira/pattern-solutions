package br.com.diogo.pattern.solutions;

import br.com.diogo.pattern.solutions.builder.Builder;

@Builder
public class Classe {
	private String nome;
	private String teste;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTeste() {
		return teste;
	}
	public void setTeste(String teste) {
		this.teste = teste;
	}
	
}
