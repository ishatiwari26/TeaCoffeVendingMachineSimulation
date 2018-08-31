package com.yash.actions;

import com.yash.exceptions.WrongInputException;
import com.yash.model.User;
import com.yash.util.UserInputScanner;

public class ValidateUserAction {

	String user_type = null;
	Integer user_id = 0;
	UserInputScanner input = new UserInputScanner();

	public int validateUser() {
		System.out.print("Enter values and press Enter  ::\n1=Customer ,2=Operator : \n");
		try {
			user_id = input.getIntValue();
			User user = new User();
			if (user.getUsers().containsKey(user_id)) {
				user_type = user.getUsers().get(user_id);
				System.out.println("You are ::" + user_type);
			} else if (user_id == 0) {
				System.out.println("User is succeffully logged out from system.");
				user_type = "unknown user";
			} else {
				user_type = "unknown user";
				throw new WrongInputException("Warning :: Invalid User !!!");
			}
		} catch (Exception e) {
			user_type = "unknown user";
		}
		return user_id;
	}
}
