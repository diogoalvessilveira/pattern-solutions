package br.com.diogo.pattern.solutions.tests;

import java.util.Observable;

public class MyObservable extends Observable {

	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

}
