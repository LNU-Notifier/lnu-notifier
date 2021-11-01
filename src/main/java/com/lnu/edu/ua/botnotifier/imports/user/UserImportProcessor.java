package com.lnu.edu.ua.botnotifier.imports.user;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportWriter;

import generated.imports.dataobjects.User;
import generated.imports.dataobjects.Users;

public class UserImportProcessor implements IUserImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserImportProcessor.class);

	private UserDbi mapToDbi(User user) {
		UserDbi userDbi = new UserDbi();
		userDbi.setUsername(user.getUsername());
		userDbi.setPassword(user.getPassword());
		userDbi.setEmail(user.getEmail());
		userDbi.setRegistrationTime(Timestamp.from(Instant.now()));
		return userDbi;
	}

	@Override
	public void execute(Users users) {
		for (User user : users.getUser()) {
			UserDbi userDbi = mapToDbi(user);
			userImportWriter.write(userDbi);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserImportWriter userImportWriter;

	@Required
	public void setUserImportWriter(IUserImportWriter userImportWriter) {
		this.userImportWriter = userImportWriter;
	}

}
