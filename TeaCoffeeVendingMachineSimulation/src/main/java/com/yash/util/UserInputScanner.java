package com.yash.util;

import java.util.Scanner;

public class UserInputScanner {
	private Scanner userInputScanner = new Scanner(System.in);

	public int getIntValue() {
		return userInputScanner.nextInt();
	}
}
