package br.com.diogo.pattern.solutions.template.tests;

import java.util.function.Consumer;

import br.com.diogo.pattern.solutions.template.AbstractTemplate;

public class Template extends AbstractTemplate<CustomTemplate> {

	private CustomTemplate template;

	public Template(CustomTemplate template) {
		set(template);
	}

	@Override
	public void with(Consumer<CustomTemplate> consumer) {
		consumer.accept(get());
		get().getValue();
	}

	@Override
	public CustomTemplate get() {
		return template;
	}

	@Override
	public void set(CustomTemplate template) {
		this.template = template;
	}

}
