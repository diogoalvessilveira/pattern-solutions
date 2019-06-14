package br.com.diogo.pattern.solutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diogo.pattern.solutions.stragegy.IStrategy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTests {

	@Autowired
	private IStrategy strategy;

	@Test
	public void strategy() {
		strategy.getStrategy(EstrategiaTeste.class, "TESTE2").teste();
		strategy.getStrategy(EstrategiaTeste.class, "TESTE").teste();
	}

}
