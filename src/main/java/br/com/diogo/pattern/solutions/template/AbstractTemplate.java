package br.com.diogo.pattern.solutions.template;

import java.util.function.Consumer;

public abstract class AbstractTemplate<I> {
	public abstract void with(Consumer<I> consumer);

	public abstract I get();

	public abstract void set(I template);
}
