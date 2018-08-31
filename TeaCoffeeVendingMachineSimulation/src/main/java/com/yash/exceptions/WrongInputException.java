package com.yash.exceptions;

public class WrongInputException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public WrongInputException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
