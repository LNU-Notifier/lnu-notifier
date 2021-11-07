package com.lnu.edu.ua.botnotifier.api.mappers;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

import generated.imports.dataobjects.User;

public class UserMapper {

	public static UserDbi mapToDbi(User user) {
		UserDbi userDbi = new UserDbi();
		userDbi.setUsername(user.getUsername());
		userDbi.setPassword(user.getPassword());
		userDbi.setEmail(user.getEmail());
		return userDbi;
	}

	public static User mapFromDbi(UserDbi userDbi) {
		User user = new User();
		user.setUsername(userDbi.getUsername());
		user.setPassword(userDbi.getPassword());
		user.setEmail(userDbi.getEmail());
		return user;
	}

}
