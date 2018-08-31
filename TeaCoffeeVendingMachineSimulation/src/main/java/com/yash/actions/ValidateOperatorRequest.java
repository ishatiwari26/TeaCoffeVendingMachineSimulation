package com.yash.actions;

import java.util.Scanner;

import com.yash.service.VendingMachineService;
import com.yash.serviceImpl.VendingMachineServiceImpl;

public class ValidateAdminRequest {
	private VendingMachineService vendingMachineService = new VendingMachineServiceImpl();

	public void adminRequests() {
		Scanner input = new Scanner(System.in);
		Integer operation_id = 0, countRefilling = 0;
		System.out.print(
				"Enter values for below Options  ::\n1=Refill Container ,2=Check Total Sale, 3=Container Status  , 4=Reset Container, 0=Exit : \n");
		try {
			operation_id = input.nextInt();
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
				System.out.println("Now the containe status is :: " + vendingMachineService.getTotalSales());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
