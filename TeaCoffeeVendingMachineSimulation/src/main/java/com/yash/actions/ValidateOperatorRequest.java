package com.yash.actions;

import com.yash.service.VendingMachineService;
import com.yash.serviceImpl.VendingMachineServiceImpl;
import com.yash.util.UserInputScanner;

public class ValidateOperatorRequest {

	private VendingMachineService vendingMachineService = new VendingMachineServiceImpl();
	UserInputScanner input = new UserInputScanner();
	Integer operation_id = 0, countRefilling = 0;

	public void operatorRequests() {
		System.out.print(
				"Enter values for below Options  ::\n1=Refill Container ,2=Check Total Sale, 3=Container Status  , 4=Reset Container, 0=Exit : \n");
		try {
			operation_id = input.getIntValue();
			if (operation_id == 1) {
				countRefilling++;
				vendingMachineService.RefillContainers();
				System.out.println("Refilling done. No of times refilling :: " + countRefilling);
			} else if (operation_id == 2) {
				System.out.println("Current Total Sale of products :: " + vendingMachineService.getTotalSales());
			} else if (operation_id == 3) {
				vendingMachineService.printStats();
			} else if (operation_id == 4) {
				vendingMachineService.reset();
				System.out.println("Your container is reset now.");
				System.out.println("Now the contain status is :: " + vendingMachineService.getTotalSales());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
