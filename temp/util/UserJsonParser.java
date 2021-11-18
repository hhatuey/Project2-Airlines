package com.project.util;

import java.util.LinkedHashMap;

import com.project.models.User;

public class UserJsonParser {

	private static UserJsonParser ujp;
	
	public static synchronized UserJsonParser getUserJsonParser() {
		if(ujp == null) {
			return new UserJsonParser();
		}
		return ujp;
	}
	
	public User parse(LinkedHashMap<String, Object> userJson) {
		int userId = Integer.parseInt((userJson.get("userId")).toString());
		String firstName = userJson.get("firstName").toString();
		String lastName = userJson.get("lastName").toString();
		String username = userJson.get("username").toString();
		String password = userJson.get("password").toString();
		int roleId = Integer.parseInt((userJson.get("roleId")).toString());
		String email = userJson.get("email").toString();
		User u = new User(userId, username, password, firstName, lastName, roleId, email);
		return u;
	}
	
}
