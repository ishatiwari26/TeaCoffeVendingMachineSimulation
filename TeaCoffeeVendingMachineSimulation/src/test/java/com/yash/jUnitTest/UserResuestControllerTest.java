package com.yash.jUnitTest;

import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.actions.ValidateCustomerRequest;
import com.yash.actions.ValidateOperatorRequest;
import com.yash.actions.ValidateUserAction;
import com.yash.controller.UserResuestController;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.util.UserInputScanner;

@RunWith(MockitoJUnitRunner.class)
public class UserResuestControllerTest {
	private HashMap<String, Object> orderedProduts = new HashMap<String, Object>();
	@InjectMocks
	private UserResuestController userRequest;

	@Mock
	private ValidateUserAction userAction;
	@Mock
	private ValidateCustomerRequest validateCustomerRequest;
	@Mock
	private ValidateOperatorRequest validateOperatorRequest;
	@Mock
	private UserInputScanner userInputScanner;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldTakeUserRequestWhenUserIsCustomer() {
		when(userAction.validateUser()).thenReturn(1).thenReturn(0);
		orderedProduts.put(Item.COFFEE.getName(), Coin.FIVE);
		when(validateCustomerRequest.customerRequests()).thenReturn(orderedProduts);
		userRequest.requestProcessor();
	}

	@Test
	public void shouldTakeUserRequestWhenUserIsOperator() {
		when(userAction.validateUser()).thenReturn(2).thenReturn(0);
		userRequest.requestProcessor();
	}
}
