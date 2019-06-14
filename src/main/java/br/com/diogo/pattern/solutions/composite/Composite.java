package br.com.diogo.pattern.solutions.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Composite<T> {
	private List<T> folhas;

	public Composite() {
		if (Objects.isNull(folhas)) {
			folhas = new ArrayList<>();
		}
	}

	public void add(T folha) {
		Objects.requireNonNull(folha);
		folhas.add(folha);
	}

	public void remove(T folha) {
		Objects.requireNonNull(folha);
		folhas.remove(folha);
	}

	public List<T> getFolhas() {
		return folhas;
	}
}
