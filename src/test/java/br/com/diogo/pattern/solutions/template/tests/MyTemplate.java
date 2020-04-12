package br.com.diogo.pattern.solutions.template.tests;

public class MyTemplate implements CustomTemplate {

	@Override
	public String getValue() {
		return "Valor";
	}

	@Override
	public Object getObject() {
		return new Integer(1);
	}

}
