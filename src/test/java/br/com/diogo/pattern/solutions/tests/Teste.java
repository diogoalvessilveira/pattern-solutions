package br.com.diogo.pattern.solutions.tests;

public enum Teste {
	TESTE(1, "Um"), TESTE2(2, "Dois");

	private int codigo;
	private String descricao;

	private Teste(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
