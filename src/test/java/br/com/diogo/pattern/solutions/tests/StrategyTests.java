package br.com.diogo.pattern.solutions.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diogo.pattern.solutions.pattern.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTests {

	@Autowired
	private Pattern strategy;

	@Test
	public void strategy() {
		String teste2 = strategy.getStrategy(EstrategiaTeste.class, "TESTE2").teste();
		String teste1 = strategy.getStrategy(EstrategiaTeste.class, "TESTE").teste();
		assertEquals(teste2, "Executou B");
		assertEquals(teste1, "Executou A");
	}

}
