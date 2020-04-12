package br.com.diogo.pattern.solutions.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Visitor<R> implements Function<Object, R> {

	private Map<Class<?>, Function<Object, R>> mapaVisitors = new HashMap<>();

	@Override
	public R apply(Object object) {
		return mapaVisitors.get(object.getClass()).apply(object);
	}

	public <C> AcceptVisitor<R, C> on(Class<C> classe) {
		return new AcceptVisitor<>(this, classe);
	}

	public static class AcceptVisitor<R, C> {
		private final Visitor<R> visitor;
		private final Class<C> classe;

		public AcceptVisitor(Visitor<R> visitor, Class<C> classe) {
			this.visitor = visitor;
			this.classe = classe;
		}

		@SuppressWarnings("unchecked")
		public Visitor<R> then(Function<C, R> function) {
			visitor.mapaVisitors.put(classe, (Function<Object, R>) function);
			return visitor;
		}
	}
}
