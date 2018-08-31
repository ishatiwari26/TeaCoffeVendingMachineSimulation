package com.yash.jUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controller.ProductRequest;
import com.yash.controller.UserResuestController;

@RunWith(MockitoJUnitRunner.class)
public class ProductRequestTest {
	@InjectMocks
	private ProductRequest productRequest;
	@Mock
	private UserResuestController userRequestController;

	@Test
	public void shouldCallMainProcess() {
		ProductRequest.main(new String[] { "TEA" });
	}
}
