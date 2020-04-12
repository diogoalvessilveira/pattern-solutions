package br.com.diogo.pattern.solutions.observer.tests;

import java.util.Observable;
import java.util.Observer;

import br.com.diogo.pattern.solutions.observer.ObserverPattern;

@ObserverPattern(observable = MyObservable.class)
public class MyObserver3 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Executou my observer3");
	}

}
