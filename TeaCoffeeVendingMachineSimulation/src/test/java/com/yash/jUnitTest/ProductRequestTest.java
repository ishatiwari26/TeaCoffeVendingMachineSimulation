package com.yash.jUnitTest;

import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.actions.ValidateUserAction;
import com.yash.controller.ProductRequest;
import com.yash.controller.UserResuestController;
import com.yash.util.UserInputScanner;

@RunWith(MockitoJUnitRunner.class)
public class ProductRequestTest {
	@InjectMocks
	private ProductRequest productRequest;
	@Mock
	private UserResuestController userRequestController;
	@Mock
	private UserInputScanner userInputScanner;
	@Mock
	private ValidateUserAction userAction;

	@Test
	public void shouldCallMainProcess() {
		doNothing().when(userRequestController).requestProcessor();
		// when(userInputScanner.getIntValue()).thenReturn(0);
		// when(userAction.validateUser()).thenReturn(0);
		String[] args = null;
		ProductRequest.main(args);
	}
}
