package com.yash.enums;

public enum Coin {
	ONE(1), TWO(2), FIVE(5), TEN(10);

	private int rupee;

	private Coin(int rupee) {
		this.rupee = rupee;
	}

	public int getAmount() {
		return rupee;
	}
}
