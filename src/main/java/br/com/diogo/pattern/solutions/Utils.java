package br.com.diogo.pattern.solutions;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {
	private Utils() {
	}

	public static boolean nuloOuVazio(Object objeto) {
		return objeto == null;
	}

	public static boolean nuloOuVazio(String valor) {
		return StringUtils.isBlank(valor);
	}

	public static boolean nuloOuVazio(Collection<?> colecao) {
		return CollectionUtils.isEmpty(colecao);
	}
}
