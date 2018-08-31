package com.yash.jUnitTest;

import org.junit.Test;

import com.yash.exceptions.NotFullPaidException;

public class NotFullPaidExceptionTest {

	@Test
	public void shouldReturnNotFullPaidMessageWithRemainingFund() {
		NotFullPaidException notFulPaid = new NotFullPaidException("NotFullPaidException", 1);
		String message = notFulPaid.getMessage();
		System.out.println(message);
	}

	@Test
	public void shouldReturnRemainingFund() {
		NotFullPaidException notFulPaid = new NotFullPaidException("NotFullPaidException", 1);
		Long remainingFund = notFulPaid.getRemaining();
		System.out.println(remainingFund);
	}

}
