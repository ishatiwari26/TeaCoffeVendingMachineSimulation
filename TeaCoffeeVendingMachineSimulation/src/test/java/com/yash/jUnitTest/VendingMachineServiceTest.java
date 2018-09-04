package com.yash.jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.daoImpl.VendingMachineDaoImpl;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.Container;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineServiceTest {
	@InjectMocks
	private VendingMachineServiceImpl vendingMachineServiceImpl;

	@Mock
	private VendingMachineDaoImpl vendingMachineDaoImpl;

	@Test
	public void shouldInitializeContainerWhenRefillingDone() {
		doNothing().when(vendingMachineDaoImpl).initialize();
		vendingMachineServiceImpl.RefillContainers();
	}

	@Test
	public void shouldGetTotalSales() {
		when(vendingMachineDaoImpl.getTotalSales()).thenReturn(new Long(100));
		vendingMachineServiceImpl.getTotalSales();
	}

	@Test
	public void shouldGetItemPriceWhenSelectAnyProduct() {
		when(vendingMachineDaoImpl.selectItemAndGetPrice(Item.BLACKCOFFEE)).thenReturn(10l);
		long itemPrice = vendingMachineServiceImpl.selectItemAndGetPrice(Item.BLACKCOFFEE);
		assertEquals(itemPrice, vendingMachineServiceImpl.selectItemAndGetPrice(Item.BLACKCOFFEE));
	}

	@Test
	public void shouldContainerReset() {
		doNothing().when(vendingMachineDaoImpl).reset();
		vendingMachineServiceImpl.reset();
	}

	@Test
	public void shouldPrintRwportWhenAskByOperator() {
		doNothing().when(vendingMachineDaoImpl).printStats();
		vendingMachineServiceImpl.printStats();
	}

	@Test
	public void shouldInsertCoin() {
		doNothing().when(vendingMachineDaoImpl).insertCoin(Coin.TEN);
		vendingMachineServiceImpl.insertCoin(Coin.TEN);
	}

	@Test
	public void shouldRefundChange() {
		List<Coin> getAmountChange = getChangeOfCoins();
		when(vendingMachineDaoImpl.refund()).thenReturn(getAmountChange);
		coinsChange = vendingMachineServiceImpl.refund();
		assertEquals(getAmountChange, coinsChange);
	}

	@Test
	public void shouldCollectProductAndChange() {
		List<Coin> getAmountChange = getChangeOfCoins();
		Container<Item, List<Coin>> bucket = new Container<Item, List<Coin>>(Item.COFFEE, getAmountChange);
		when(vendingMachineDaoImpl.collectItemAndChange()).thenReturn(bucket);
		vendingMachineServiceImpl.collectItemAndChange();
		assertEquals(bucket, vendingMachineServiceImpl.collectItemAndChange());
	}

	List<Coin> coinsChange = new ArrayList<Coin>();

	private List<Coin> getChangeOfCoins() {
		List<Coin> getAmountChange = new ArrayList<Coin>();
		getAmountChange.add(Coin.TEN);
		return getAmountChange;
	}

}
