package br.com.diogo.pattern.solutions.builder;

import java.util.function.Consumer;

public final class Builder<T> {

	private T objeto;

	private Builder(Class<T> classe) {
		try {
			objeto = classe.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Builder<T> with(Consumer<T> consumer) {
		consumer.accept(objeto);
		return this;
	}

	public T toBuilder() {
		return objeto;
	}

	public static <T> Builder<T> build(Class<T> classe) {
		return new Builder<>(classe);
	}

}