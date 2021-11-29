package com.lnu.edu.ua.botnotifier.api.mappers;

import com.lnu.edu.ua.botnotifier.api.dataobjects.User;
import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public class UserMapper {

	public static UserDbi mapToDbi(User user) {
		if(user == null) {
			return null;
		}
		UserDbi userDbi = new UserDbi();
		userDbi.setId(user.getId());
		userDbi.setUserName(user.getUserName());
		userDbi.setFirstName(user.getFirstName());
		userDbi.setLastName(user.getLastName());
		userDbi.setGroupCode(user.getGroupCode());
		userDbi.setSubGroup(user.getSubGroup());
		userDbi.setTypeOfWeek(user.getTypeOfWeek());
		userDbi.setWeekDay(user.getWeekDay());
		return userDbi;
	}

	public static User mapFromDbi(UserDbi userDbi) {
		if(userDbi == null) {
			return null;
		}
		User user = new User();
		user.setId(userDbi.getId());
		user.setUserName(userDbi.getUserName());
		user.setFirstName(userDbi.getFirstName());
		user.setLastName(userDbi.getLastName());
		user.setGroupCode(userDbi.getGroupCode());
		user.setSubGroup(userDbi.getSubGroup());
		user.setTypeOfWeek(userDbi.getTypeOfWeek());
		user.setWeekDay(userDbi.getWeekDay());
		return user;
	}

}
