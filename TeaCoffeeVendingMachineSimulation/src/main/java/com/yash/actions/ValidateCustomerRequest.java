package com.yash.actions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.yash.enums.Coin;
import com.yash.enums.Item;
import com.yash.service.VendingMachineService;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.Container;

public class ValidateCustomerRequest {
	private VendingMachineService vendingMachineService = new VendingMachineServiceImpl();

	public HashMap<String, Object> customerRequests() {
		Integer drink_id = 0, coins = 0;
		String productName = "";
		HashMap<String, Object> master_map = new HashMap<String, Object>();
		vendingMachineService.RefillContainers();
		Scanner input = new Scanner(System.in);
		System.out
				.print("Enter values for Drink type  ::\n1=Tea ,2=Black Tea , 3=Coffee  , 4=Black Coffee, 0=Exit : \n");
		try {
			drink_id = input.nextInt();
			long price = 0;
			if (drink_id == 1) {
				price = vendingMachineService.selectItemAndGetPrice(Item.TEA);
				productName = Item.TEA.getName();
			} else if (drink_id == 2) {
				price = vendingMachineService.selectItemAndGetPrice(Item.BLACKTEA);
				productName = Item.BLACKTEA.getName();
			} else if (drink_id == 3) {
				price = vendingMachineService.selectItemAndGetPrice(Item.COFFEE);
				productName = Item.COFFEE.getName();
			} else if (drink_id == 4) {
				price = vendingMachineService.selectItemAndGetPrice(Item.BLACKCOFFEE);
				productName = Item.BLACKCOFFEE.getName();
			} else {
				System.out.println("You have wrong input.");
				System.exit(0);
			}

			System.out.print("The Price of :: " + productName + " is :: " + price);
			System.out.print(
					"\n Enter Coins. Enter 1=1 rs coin , Enter 2=2 rs coin, Enter 3=5 rs coin, Enter 4=10 rs coin, 0=Exit ; \n");
			coins = input.nextInt();
			String longNumber = String.valueOf(coins);
			List<Integer> coinCount = Arrays.stream(longNumber.split("\\B")).map(s -> Integer.valueOf(s))
					.collect(Collectors.toList());
			for (Integer coinInput : coinCount) {
				if (coinInput == 1)
					vendingMachineService.insertCoin(Coin.ONE);
				else if (coinInput == 2)
					vendingMachineService.insertCoin(Coin.TWO);
				else if (coinInput == 3)
					vendingMachineService.insertCoin(Coin.FIVE);
				else if (coinInput == 4)
					vendingMachineService.insertCoin(Coin.TEN);

			}

			Container<Item, List<Coin>> bucket = vendingMachineService.collectItemAndChange();
			Item item = bucket.getFirst();
			List<Coin> change = bucket.getSecond();
			master_map.put(item.getName(), change.isEmpty() ? null : change.get(0).getAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master_map;

	}
}
