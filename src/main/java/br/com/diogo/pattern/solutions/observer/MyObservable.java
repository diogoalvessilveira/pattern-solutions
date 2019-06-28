package br.com.diogo.pattern.solutions.observer;

import java.util.Observable;

public class MyObservable extends Observable {

	public void notifyObservers() {
		String[] a = { "MyObserver", "MyObserver2" };

		for (String s : a) {
			setChanged();
			notifyObservers(s);
		}
	}

}
