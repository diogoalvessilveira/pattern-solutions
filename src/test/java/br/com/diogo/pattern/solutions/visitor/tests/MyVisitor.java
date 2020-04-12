package br.com.diogo.pattern.solutions.visitor.tests;

public class MyVisitor {
	private String valor;

	public MyVisitor(String valor) {
		setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
