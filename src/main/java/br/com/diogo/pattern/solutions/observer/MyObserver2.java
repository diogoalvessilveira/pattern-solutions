package br.com.diogo.pattern.solutions.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver2 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Executou my observer2" + arg.toString());
	}

}
