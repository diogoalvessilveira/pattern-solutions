package br.com.diogo.pattern.solutions.pattern;

import java.util.Observable;

public interface Pattern {
	<T> T getStrategy(Class<T> interfaceUtilizadaComoEstrategia, String enumAtual);

	<T extends Observable> T getObserver(Class<T> observable);
}
