package br.com.diogo.pattern.solutions.visitor.tests;

public class MyVisitor2 {
	private Double valor;

	public MyVisitor2(Double valor) {
		setValor(valor);
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
