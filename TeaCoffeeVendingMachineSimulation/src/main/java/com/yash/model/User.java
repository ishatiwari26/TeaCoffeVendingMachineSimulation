package com.yash.model;

import java.util.HashMap;

public class User {
	@SuppressWarnings("serial")
	private final static HashMap<Integer, String> user_map = new HashMap<Integer, String>() {
		{
			put(1, "Customer");
			put(2, "Operator");

		}
	};

	public HashMap<Integer, String> getUsers() {
		return user_map;
	}

}
