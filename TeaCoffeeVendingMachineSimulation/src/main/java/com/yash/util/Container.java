package com.yash.util;

public class Container<E1, E2> {
	private E1 first;
	private E2 second;

	public Container(E1 first, E2 second) {
		this.first = first;
		this.second = second;
	}

	public E1 getFirst() {
		return first;
	}

	public E2 getSecond() {
		return second;
	}
}
