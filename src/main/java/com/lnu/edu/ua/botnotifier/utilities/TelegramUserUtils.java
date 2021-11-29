package com.lnu.edu.ua.botnotifier.utilities;

import org.telegram.telegrambots.meta.api.objects.User;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public class TelegramUserUtils {
	
	public static UserDbi mapToDbi(User user) {
		if(user == null) {
			return null;
		}
		UserDbi userDbi = new UserDbi();
		userDbi.setId(user.getId());
		userDbi.setUserName(user.getUserName());
		userDbi.setFirstName(user.getFirstName());
		userDbi.setLastName(user.getLastName());
		return userDbi;
	}

}
