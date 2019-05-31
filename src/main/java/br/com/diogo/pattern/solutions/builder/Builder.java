package br.com.diogo.pattern.solutions.builder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface Builder {
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Default {
	}

	String nomeMetodoBuilder() default "builder";

	String nomeMetodoBuild() default "build";

	String nomeClasseBuilder() default "";

	boolean toBuilder() default false;

	@Target({ ElementType.FIELD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ObtemValor {
		String campo() default "";

		String metodo() default "";

		boolean isStatic() default false;
	}
}
