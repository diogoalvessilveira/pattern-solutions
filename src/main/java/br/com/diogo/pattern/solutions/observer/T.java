package br.com.diogo.pattern.solutions.observer;

public class T {
	public static void main(String[] args) {
		MyObservable m = new MyObservable();
		MyObserver o = new MyObserver();
		MyObserver2 o1 = new MyObserver2();
		MyObserver3 o2 = new MyObserver3();
		m.addObserver(o);
		m.addObserver(o1);
		m.addObserver(o2);
		m.notifyObservers();
	}
}
