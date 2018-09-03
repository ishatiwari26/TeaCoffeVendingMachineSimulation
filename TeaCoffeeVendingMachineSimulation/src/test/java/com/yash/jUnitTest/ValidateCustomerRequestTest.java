package com.yash.jUnitTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.actions.ValidateCustomerRequest;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.Container;
import com.yash.util.UserInputScanner;

@RunWith(MockitoJUnitRunner.class)
public class ValidateCustomerRequestTest {
	private HashMap<String, Object> customerOrders = new HashMap<String, Object>();
	@InjectMocks
	private ValidateCustomerRequest validateCustomerRequest;
	@Mock
	private VendingMachineServiceImpl vendingMachineServiceImpl;
	@Mock
	private UserInputScanner userInputScanner;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldGetOrderOfTeaWhenCustomerGiveOrderWithMoney() {
		when(userInputScanner.getIntValue()).thenReturn(1).thenReturn(4);
		when(vendingMachineServiceImpl.selectItemAndGetPrice(Item.TEA)).thenReturn(new Long(10));
		doNothing().when(vendingMachineServiceImpl).insertCoin(Matchers.any(Coin.class));
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.TEN);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.TEA, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldGetOrderOfBlackTeaWhenCustomerGiveOrderWithMoney() {
		when(userInputScanner.getIntValue()).thenReturn(2).thenReturn(3);
		when(vendingMachineServiceImpl.selectItemAndGetPrice(Item.BLACKTEA)).thenReturn(new Long(5));
		doNothing().when(vendingMachineServiceImpl).insertCoin(Matchers.any(Coin.class));
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.FIVE);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.BLACKTEA, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldGetOrderOfCoffeeWhenCustomerGiveOrderWithMoney() {
		when(userInputScanner.getIntValue()).thenReturn(3).thenReturn(43);
		when(vendingMachineServiceImpl.selectItemAndGetPrice(Item.COFFEE)).thenReturn(new Long(15));
		doNothing().when(vendingMachineServiceImpl).insertCoin(Matchers.any(Coin.class));
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.FIVE);
		coins.add(Coin.TEN);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.COFFEE, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldGetOrderOfBlackCoffeeWhenCustomerGiveOrderWithMoney() {
		when(userInputScanner.getIntValue()).thenReturn(4).thenReturn(1);
		when(vendingMachineServiceImpl.selectItemAndGetPrice(Item.BLACKCOFFEE)).thenReturn(new Long(10));
		doNothing().when(vendingMachineServiceImpl).insertCoin(Matchers.any(Coin.class));
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.FIVE);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.BLACKCOFFEE, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldGetOrderWhenCustomerGiveOrderWithLessMoney() {
		when(userInputScanner.getIntValue()).thenReturn(4).thenReturn(2);
		when(vendingMachineServiceImpl.selectItemAndGetPrice(Item.BLACKCOFFEE)).thenReturn(new Long(10));
		doNothing().when(vendingMachineServiceImpl).insertCoin(Matchers.any(Coin.class));
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(Coin.FIVE);
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.BLACKCOFFEE, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldHandLessMoneyWhenOrderCoffeeWithLessAmount() {
		when(userInputScanner.getIntValue()).thenReturn(4).thenReturn(4);
		vendingMachineServiceImpl.selectItemAndGetPrice(Item.COFFEE);
		vendingMachineServiceImpl.insertCoin(Coin.TEN);
		List<Coin> coins = new ArrayList<Coin>();
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.COFFEE, coins);
		when(vendingMachineServiceImpl.collectItemAndChange()).thenReturn(bucket);
		customerOrders = validateCustomerRequest.customerRequests();
		assertNotNull(customerOrders);

	}

	@Test
	public void shouldGiveMessageWhenHaveWrongOrder() {
		when(userInputScanner.getIntValue()).thenReturn(5);
		validateCustomerRequest.customerRequests();

	}

	@Test
	public void shouldHandleInputMismatchExceptionWhenEnterwrongValue() {
		doThrow(InputMismatchException.class).when(userInputScanner).getIntValue();
		validateCustomerRequest.customerRequests();
	}

}
