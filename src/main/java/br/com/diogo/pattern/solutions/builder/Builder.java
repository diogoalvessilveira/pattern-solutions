package br.com.diogo.pattern.solutions.builder;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class Builder<T> {

	private T objeto;
	private Stream.Builder<T> streamBuilder;

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

	public void add(T objeto) {
		Objects.requireNonNull(objeto);

		if (Objects.isNull(getStreamBuilder())) {
			setStreamBuilder(Stream.builder());
		}

		getStreamBuilder().add(objeto);
	}

	public Stream<T> getStream() {
		return getStreamBuilder().build();
	}

	private Stream.Builder<T> getStreamBuilder() {
		return streamBuilder;
	}

	private void setStreamBuilder(Stream.Builder<T> builder) {
		this.streamBuilder = builder;
	}
}