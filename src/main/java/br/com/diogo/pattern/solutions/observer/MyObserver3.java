package br.com.diogo.pattern.solutions.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver3 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Executou my observer3" + arg.toString());
	}

}
