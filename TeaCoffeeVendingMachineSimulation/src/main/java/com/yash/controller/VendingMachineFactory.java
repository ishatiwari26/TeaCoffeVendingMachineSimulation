package com.yash.controller;

import com.yash.service.VendingMachineService;
import com.yash.serviceImpl.VendingMachineServiceImpl;

public class VendingMachineFactory {
	public static VendingMachineService createVendingMachine() {
		return new VendingMachineServiceImpl();
	}
}