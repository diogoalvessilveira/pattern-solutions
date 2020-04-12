package br.com.diogo.pattern.solutions.observer.tests;

import java.util.Observable;

public class MyObservable extends Observable {

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

}
