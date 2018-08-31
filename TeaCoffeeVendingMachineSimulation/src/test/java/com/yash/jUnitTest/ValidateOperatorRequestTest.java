package com.yash.jUnitTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.InputMismatchException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.actions.ValidateOperatorRequest;
import com.yash.daoImpl.VendingMachineDaoImpl;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.UserInputScanner;

@RunWith(MockitoJUnitRunner.class)
public class ValidateOperatorRequestTest {

	@InjectMocks
	private ValidateOperatorRequest validateOperatorRequest;

	@Mock
	private VendingMachineDaoImpl vendingMachineDaoImpl;
	@Mock
	private VendingMachineServiceImpl vendingMachineServiceImpl;
	@Mock
	private UserInputScanner userInputScanner;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldValidateOperatorUserWhenRefillingContainer() {
		when(userInputScanner.getIntValue()).thenReturn(1);
		validateOperatorRequest.operatorRequests();
	}

	@Test
	public void shouldValidateOperatorUserWhenGetTotalSales() {
		when(userInputScanner.getIntValue()).thenReturn(2);
		validateOperatorRequest.operatorRequests();
	}

	@Test
	public void shouldValidateOperatorUserWhenPrintStats() {
		when(userInputScanner.getIntValue()).thenReturn(3);
		doNothing().when(vendingMachineServiceImpl).printStats();
		validateOperatorRequest.operatorRequests();
		verify(userInputScanner).getIntValue();
		verify(vendingMachineServiceImpl).printStats();

	}

	@Test
	public void shouldValidateOperatorUserWhenResetContainer() {
		when(userInputScanner.getIntValue()).thenReturn(4);
		validateOperatorRequest.operatorRequests();
	}

	@Test
	public void shouldHandleInputMismatchExceptionWhenEnterwrongValue() {
		doThrow(InputMismatchException.class).when(userInputScanner).getIntValue();
		validateOperatorRequest.operatorRequests();
	}

}
