package com.yash.jUnitTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controller.VendingMachineFactory;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.Container;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineFactoryTest {
	@InjectMocks
	private VendingMachineFactory vendingMachineFactory;
	@Mock
	private VendingMachineServiceImpl VendingMachineServiceImpl;

	@Test
	public void shouldCallProductRequestByMachineFactory() {
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.TEN);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.TEA, coins);
		when(VendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		VendingMachineFactory.createVendingMachine();
	}
}
