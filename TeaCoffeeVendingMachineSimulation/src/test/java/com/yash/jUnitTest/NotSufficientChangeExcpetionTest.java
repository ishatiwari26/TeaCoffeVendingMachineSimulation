package com.yash.jUnitTest;

import org.junit.Test;

import com.yash.exceptions.NotSufficientChangeException;

public class NotSufficientChangeExcpetionTest {

	@Test
	public void shouldReturnNotSuffiecientChange() {
		NotSufficientChangeException noSuffiecientChange = new NotSufficientChangeException(
				"NotSufficientChangeException");
		noSuffiecientChange.getMessage();
	}
}
