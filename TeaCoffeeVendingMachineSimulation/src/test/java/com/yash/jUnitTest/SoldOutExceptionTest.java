package com.yash.jUnitTest;

import org.junit.Test;

import com.yash.exceptions.SoldOutException;

public class SoldOutExceptionTest {
	@Test
	public void shouldReturnSoldOut() {
		SoldOutException soldOut = new SoldOutException("SoldOutException");
		soldOut.getMessage();
	}
}
