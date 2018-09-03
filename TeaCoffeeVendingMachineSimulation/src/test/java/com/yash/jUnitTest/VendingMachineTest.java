package com.yash.jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controller.VendingMachineFactory;
import com.yash.daoImpl.VendingMachineDaoImpl;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.exceptions.NotFullPaidException;
import com.yash.exceptions.NotSufficientChangeException;
import com.yash.exceptions.SoldOutException;
import com.yash.service.VendingMachineService;
import com.yash.util.Container;
import com.yash.util.Inventory;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
	@InjectMocks
	VendingMachineDaoImpl vendingMachineDaoImpl;
	@Mock
	private Inventory<Coin> cashInventory;
	@Mock
	private Inventory<Item> itemInventory;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setUp() {
		vendingMachineService = VendingMachineFactory.createVendingMachine();
	}

	@Test
	public void shouldBuyItemWithExactPrice() {

		long price = vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
		vendingMachineService.insertCoin(Coin.TEN);
		vendingMachineService.insertCoin(Coin.TEN);

		Container<Item, List<Coin>> bucket = vendingMachineService.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();

		assertEquals(Item.COFFEE.getPrice(), price);
		assertEquals(Item.COFFEE, item);
		assertEquals(Coin.FIVE, change.get(0));
		assertTrue(!change.isEmpty());
	}

	@Test
	public void shouldBuyItemWithMorePrice() {
		long price = vendingMachineService.selectItemAndGetPrice(Item.TEA);
		vendingMachineService.insertCoin(Coin.FIVE);
		vendingMachineService.insertCoin(Coin.TEN);

		Container<Item, List<Coin>> bucket = vendingMachineService.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		assertEquals(Item.TEA, item);
		assertTrue(!change.isEmpty());
		assertEquals(Item.TEA.getPrice(), price);
		// assertEquals(15 - Item.TEA.getPrice(), getTotal(change));

	}

	@Test
	public void shouldMoneyRefund() {
		long price = vendingMachineService.selectItemAndGetPrice(Item.BLACKCOFFEE);
		vendingMachineService.insertCoin(Coin.TEN);
		vendingMachineService.insertCoin(Coin.FIVE);
		vendingMachineService.insertCoin(Coin.TWO);
		vendingMachineService.insertCoin(Coin.ONE);
		assertEquals(Item.BLACKCOFFEE.getPrice(), price);

		assertEquals(18, getTotal(vendingMachineService.refund()));
	}

	@Test
	public void whenBuyMoreItemsWhichNotInStockThenReturnSoldOut() {
		exception.expect(SoldOutException.class);
		for (int i = 0; i < 10; i++) {
			vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
			vendingMachineService.insertCoin(Coin.TEN);
			vendingMachineService.insertCoin(Coin.FIVE);
			vendingMachineService.collectItemAndChange();
		}
	}

	@Test
	public void whenPayLessThenThrowNotFullPaid() {
		exception.expect(NotFullPaidException.class);
		exception.expectMessage("Price not full paid, remaining : 10");
		vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
		vendingMachineService.insertCoin(Coin.FIVE);
		vendingMachineService.collectItemAndChange();
	}

	@Test
	public void whenPayLessThenNotSufficientChangeException() {
		exception.expect(NotSufficientChangeException.class);
		for (int i = 0; i < 5; i++) {
			vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
			vendingMachineService.insertCoin(Coin.TEN);
			vendingMachineService.insertCoin(Coin.TEN);
			vendingMachineService.collectItemAndChange();

			vendingMachineService.selectItemAndGetPrice(Item.BLACKTEA);
			vendingMachineService.insertCoin(Coin.TEN);
			vendingMachineService.collectItemAndChange();
		}
	}

	@Test
	public void shouldThrowExceptionSoldOutWhenResetContainer() {
		exception.expect(SoldOutException.class);
		VendingMachineService vendingMachineachine = VendingMachineFactory.createVendingMachine();
		vendingMachineachine.reset();
		vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
	}

	@Test
	public void shouldReturnPrintStatusOfCotainerAndSale() {

		vendingMachineService.printStats();
	}

	@Test
	public void shouldReturnTotalSale() {
		cashInventory.add(Coin.FIVE);
		vendingMachineDaoImpl.getTotalSales();
	}

	@Test
	public void shouldHandleNotSufficientChangeWhenDeductMoney() {
		exception.expect(NotSufficientChangeException.class);
		vendingMachineDaoImpl.selectItemAndGetPrice(Item.COFFEE);
		vendingMachineDaoImpl.insertCoin(Coin.TEN);
		vendingMachineDaoImpl.insertCoin(Coin.TEN);
		cashInventory.deduct(Coin.TEN);
		vendingMachineDaoImpl.collectItemAndChange();
	}

	private static VendingMachineService vendingMachineService;

	private long getTotal(List<Coin> change) {
		long total = 0;
		for (Coin c : change) {
			total = total + c.getAmount();
		}
		return total;
	}
}
