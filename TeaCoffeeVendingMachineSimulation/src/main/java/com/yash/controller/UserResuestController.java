package com.yash.controller;

import java.util.HashMap;
import java.util.stream.Collectors;

import com.yash.actions.ValidateCustomerRequest;
import com.yash.actions.ValidateOperatorRequest;
import com.yash.actions.ValidateUserAction;

public class UserResuestController {

	private ValidateUserAction userAction = new ValidateUserAction();
	private ValidateCustomerRequest validateCustomerRequest = new ValidateCustomerRequest();
	private ValidateOperatorRequest validateOperatorRequest = new ValidateOperatorRequest();
	private HashMap<String, Object> orderedProduts = new HashMap<String, Object>();
	Integer user_id;

	public void requestProcessor() {

		System.out.println(
				"################################### Welcome To Tea Coffee Vending Machine Simulation ###################################");
		do {
			user_id = userAction.validateUser();
			if (user_id == 1) {
				orderedProduts = validateCustomerRequest.customerRequests();
				String result = orderedProduts.entrySet().stream()
						.map(entry -> entry.getKey() + " - Have change in rs :: " + entry.getValue().toString())
						.collect(Collectors.joining(", "));
				System.out.println("Your Order is :: ");
				System.out.println(result);
			} else if (user_id == 2) {
				validateOperatorRequest.operatorRequests();
				
			}
		} while (user_id > 0);
	}
}
