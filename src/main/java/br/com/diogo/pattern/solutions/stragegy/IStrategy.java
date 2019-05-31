package br.com.diogo.pattern.solutions.stragegy;

public interface IStrategy {
	<T> T getStrategy(Class<T> interfaceUtilizadaComoEstrategia, String enumAtual);
}
