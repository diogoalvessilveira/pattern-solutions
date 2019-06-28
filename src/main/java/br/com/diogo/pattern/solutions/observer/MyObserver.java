package br.com.diogo.pattern.solutions.observer;

import java.util.Observable;
import java.util.Observer;

@ObserverPattern(observable = MyObservable.class)
public class MyObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Executou my observer" + arg.toString());
	}

}
