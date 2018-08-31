package com.yash.serviceImpl;

import java.util.List;

import com.yash.dao.VendingMachineDao;
import com.yash.daoImpl.VendingMachineDaoImpl;
import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.service.VendingMachineService;
import com.yash.util.Container;

public class VendingMachineServiceImpl implements VendingMachineService {

	private VendingMachineDao vendingMachineDao = new VendingMachineDaoImpl();

	@Override
	public void insertCoin(Coin coin) {
		vendingMachineDao.insertCoin(coin);
	}

	@Override
	public List<Coin> refund() {
		return vendingMachineDao.refund();
	}

	@Override
	public Container<Item, List<Coin>> collectItemAndChange() {
		return vendingMachineDao.collectItemAndChange();
	}

	@Override
	public void reset() {
		vendingMachineDao.reset();
	}

	@Override
	public void RefillContainers() {
		vendingMachineDao.initialize();

	}

	@Override
	public long selectItemAndGetPrice(Item item) {
		return vendingMachineDao.selectItemAndGetPrice(item);
	}

	@Override
	public long getTotalSales() {
		return vendingMachineDao.getTotalSales();
	}

	@Override
	public void printStats() {
		vendingMachineDao.printStats();

	}

}
