package com.yash.jUnitTest;

import org.junit.Test;

import com.yash.exceptions.WrongInputException;

public class WrongInputExceptionTest {
	@Test
	public void shouldReturnWrongInputException() {
		WrongInputException invalidInput = new WrongInputException("NotSufficientChangeException");
		invalidInput.getMessage();
	}
}
