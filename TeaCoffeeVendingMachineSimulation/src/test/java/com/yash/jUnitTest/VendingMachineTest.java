package com.yash.jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {

	private static VendingMachineService vendingMachineService;

	@InjectMocks
	private VendingMachineDaoImpl vendingMachineDaoImpl;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setUp() {
		vendingMachineService = VendingMachineFactory.createVendingMachine();
	}

	@AfterClass
	public static void tearDown() {
		vendingMachineService = null;
	}

	@Test
	public void shouldBuyItemWithExactPrice() {
		long price = vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
		assertEquals(Item.COFFEE.getPrice(), price);
		vendingMachineService.insertCoin(Coin.TEN);
		vendingMachineService.insertCoin(Coin.TEN);

		Container<Item, List<Coin>> bucket = vendingMachineService.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		assertEquals(Item.COFFEE, item);
		assertEquals(Coin.FIVE, change.get(0));
		assertTrue(!change.isEmpty());
	}

	@Test
	public void shouldBuyItemWithMorePrice() {
		long price = vendingMachineService.selectItemAndGetPrice(Item.TEA);
		assertEquals(Item.TEA.getPrice(), price);
		vendingMachineService.insertCoin(Coin.FIVE);
		vendingMachineService.insertCoin(Coin.TEN);

		Container<Item, List<Coin>> bucket = vendingMachineService.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		assertEquals(Item.TEA, item);
		assertTrue(!change.isEmpty());
		// assertEquals(15 - Item.TEA.getPrice(), getTotal(change));

	}

	@Test
	public void shouldMoneyRefund() {
		long price = vendingMachineService.selectItemAndGetPrice(Item.BLACKCOFFEE);
		assertEquals(Item.BLACKCOFFEE.getPrice(), price);
		vendingMachineService.insertCoin(Coin.TEN);
		vendingMachineService.insertCoin(Coin.FIVE);
		vendingMachineService.insertCoin(Coin.TWO);
		vendingMachineService.insertCoin(Coin.ONE);

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

		vendingMachineachine.selectItemAndGetPrice(Item.COFFEE);

	}

	private long getTotal(List<Coin> change) {
		long total = 0;
		for (Coin c : change) {
			total = total + c.getAmount();
		}
		return total;
	}
}
