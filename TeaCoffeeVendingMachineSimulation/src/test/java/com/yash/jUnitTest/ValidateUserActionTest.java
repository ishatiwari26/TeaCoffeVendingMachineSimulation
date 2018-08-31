package com.yash.jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.actions.ValidateUserAction;
import com.yash.util.UserInputScanner;

@RunWith(MockitoJUnitRunner.class)
public class ValidateUserActionTest {
	@InjectMocks
	private ValidateUserAction validateUserAction;
	@Mock
	private UserInputScanner userInputScanner;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private Integer userID = new Integer(0);

	@Test
	public void shouldValidateUserIfUserIsCustomer() {
		when(userInputScanner.getIntValue()).thenReturn(1);
		userID = validateUserAction.validateUser();
		assertEquals(new Integer(1), userID);
	}

	@Test
	public void shouldValidateUserIfUserIsOperator() {
		when(userInputScanner.getIntValue()).thenReturn(2);
		userID = validateUserAction.validateUser();
		assertEquals(new Integer(2), userID);
	}

	@Test
	public void shouldExitFromMenuWhenInputIsZero() {
		// exception.expect(WrongInputException.class);
		when(userInputScanner.getIntValue()).thenReturn(0);
		validateUserAction.validateUser();
	}

	@Test
	public void shouldGiveWrongInputExceptionWhenGiveWrongInput() {
		when(userInputScanner.getIntValue()).thenReturn(5);
		validateUserAction.validateUser();
	}
}
