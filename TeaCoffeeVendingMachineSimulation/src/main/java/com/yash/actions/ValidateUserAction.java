package com.yash.actions;

import java.util.Scanner;

import com.yash.model.User;

public class ValidateUserAction {
	public int validateUser() {
		String user_type = null;
		Integer user_id = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter values and press Enter  ::\n1=Customer ,2=Operator : \n");
		try {
			user_id = input.nextInt();
			User user = new User();
			if (user.getUsers().containsKey(user_id)) {
				user_type = user.getUsers().get(user_id);
				System.out.println("You are ::" + user_type);
			} else {
				System.out.println("Warning :: Invalid User !!!");
				user_type = "unknown user";
			}
		} catch (Exception e) {
			user_type = "unknown user";
		}
		return user_id;
	}
}
