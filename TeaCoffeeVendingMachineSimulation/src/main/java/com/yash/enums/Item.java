package com.yash.enums;

public enum Item {
	COFFEE("Coffee", 15), TEA("Tea", 10), BLACKCOFFEE("Coffee", 10), BLACKTEA("Tea", 5);

	private String name;
	private int price;

	private Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}
}
