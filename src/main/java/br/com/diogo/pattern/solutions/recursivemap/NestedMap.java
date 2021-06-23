package br.com.diogo.pattern.solutions.recursivemap;

import java.util.HashMap;

public class NestedMap<K, V> {

	private final HashMap<K, NestedMap> child;
	private V value;

	public NestedMap() {
		child = new HashMap<>();
		value = null;
	}

	public boolean hasChild(K k) {
		return this.child.containsKey(k);
	}

	public NestedMap<K, V> getChild(K k) {
		return this.child.get(k);
	}

	public void makeChild(K k) {
		this.child.put(k, new NestedMap());
	}

	public V getValue() {
		return value;
	}

	public void setValue(V v) {
		value = v;
	}
}
