package com.yash.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.dao.VendingMachineDao;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.exceptions.NotFullPaidException;
import com.yash.exceptions.NotSufficientChangeException;
import com.yash.exceptions.SoldOutException;
import com.yash.util.Container;
import com.yash.util.Inventory;

public class VendingMachineDaoImpl implements VendingMachineDao {
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private static Inventory<Item> itemInventory = new Inventory<Item>();
	private static long totalSales;
	private Item currentItem;
	private long currentBalance;

	public VendingMachineDaoImpl() {
		initialize();
	}

	@Override
	public void initialize() {
		for (Coin c : Coin.values()) {
			cashInventory.put(c, 5);
		}

		for (Item i : Item.values()) {
			itemInventory.put(i, 5);
		}

	}

	@Override
	public long selectItemAndGetPrice(Item item) {
		if (itemInventory.hasItem(item)) {
			currentItem = item;
			return currentItem.getPrice();
		}
		throw new SoldOutException("Sold Out, Please buy another item");
	}

	@Override
	public void insertCoin(Coin coin) {
		currentBalance = currentBalance + coin.getAmount();
		cashInventory.add(coin);
	}

	@Override
	public Container<Item, List<Coin>> collectItemAndChange() {
		Item item = collectItem();
		totalSales = totalSales + currentItem.getPrice();

		List<Coin> change = collectChange();
		return new Container<Item, List<Coin>>(item, change);
	}

	private Item collectItem() throws NotSufficientChangeException, NotFullPaidException {
		if (isFullPaid()) {
			if (hasSufficientChange()) {
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			throw new NotSufficientChangeException("Not Sufficient change in Inventory");

		}
		long remainingBalance = currentItem.getPrice() - currentBalance;
		throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
	}

	private List<Coin> collectChange() {
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}

	@Override
	public List<Coin> refund() {
		List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		return refund;
	}

	private boolean isFullPaid() {
		if (currentBalance >= currentItem.getPrice()) {
			return true;
		}
		return false;
	}

	private List<Coin> getChange(long amount) throws NotSufficientChangeException {

		List<Coin> changes = new ArrayList<Coin>();

		if (amount > 0) {
			changes = new ArrayList<Coin>();
			long balance = amount;
			while (balance > 0) {
				if (balance >= Coin.TEN.getAmount() && cashInventory.hasItem(Coin.TEN)) {
					changes.add(Coin.TEN);
					balance = balance - Coin.TEN.getAmount();
					continue;

				} else if (balance >= Coin.FIVE.getAmount() && cashInventory.hasItem(Coin.FIVE)) {
					changes.add(Coin.FIVE);
					balance = balance - Coin.FIVE.getAmount();
					continue;

				} else if (balance >= Coin.TWO.getAmount() && cashInventory.hasItem(Coin.TWO)) {
					changes.add(Coin.TWO);
					balance = balance - Coin.TWO.getAmount();
					continue;

				} else if (balance >= Coin.ONE.getAmount() && cashInventory.hasItem(Coin.ONE)) {
					changes.add(Coin.ONE);
					balance = balance - Coin.ONE.getAmount();
					continue;

				} else {
					throw new NotSufficientChangeException("NotSufficientChange, Please try another product ");
				}
			}
		}

		return changes;
	}

	@Override
	public void reset() {
		cashInventory.clear();
		itemInventory.clear();
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;
	}

	@Override
	public void printStats() {
		System.out.println("Total Sales : " + totalSales);
		System.out.println("Current TEA Inventory : " + itemInventory.getQuantity(Item.TEA));
		System.out.println("Current COFFEE Inventory : " + itemInventory.getQuantity(Item.COFFEE));
		System.out.println("Current BLACK TEA Inventory : " + itemInventory.getQuantity(Item.BLACKTEA));
		System.out.println("Current BLACK COFFEE Inventory : " + itemInventory.getQuantity(Item.BLACKCOFFEE));

		System.out.println("Current Cash Inventory for ONE RS : " + cashInventory.getQuantity(Coin.ONE));
		System.out.println("Current Cash Inventory for TWO RS : " + cashInventory.getQuantity(Coin.TWO));
		System.out.println("Current Cash Inventory for FIVE RS : " + cashInventory.getQuantity(Coin.FIVE));
		System.out.println("Current Cash Inventory for TEN RS : " + cashInventory.getQuantity(Coin.TEN));
	}

	private boolean hasSufficientChange() {
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long amount) {
		boolean hasChange = true;
		try {
			getChange(amount);
		} catch (NotSufficientChangeException nsce) {
			return hasChange = false;
		}

		return hasChange;
	}

	private void updateCashInventory(List<Coin> change) {
		for (Coin c : change) {
			cashInventory.deduct(c);
		}
	}

	@Override
	public long getTotalSales() {
		return totalSales;
	}

}
