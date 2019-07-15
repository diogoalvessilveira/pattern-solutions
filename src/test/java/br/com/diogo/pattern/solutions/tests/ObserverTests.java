package br.com.diogo.pattern.solutions.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diogo.pattern.solutions.pattern.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObserverTests {

	@Autowired
	private Pattern strategy;

	@Test
	public void observer() {
		MyObservable observable = strategy.getObserver(MyObservable.class);
		int qtdObservers = observable.countObservers();
		assertTrue(qtdObservers == 2);
		observable.notifyObservers();
	}

}
