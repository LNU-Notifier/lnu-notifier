package com.lnu.edu.ua.botnotifier.imports.user;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportWriter;
import com.lnu.edu.ua.botnotifier.api.mappers.UserMapper;

import generated.imports.dataobjects.User;
import generated.imports.dataobjects.Users;

public class UserImportProcessor implements IUserImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserImportProcessor.class);

	private UserDbi createUserDbi(User user) throws Exception {
		UserDbi userDbi = UserMapper.mapToDbi(user);
		userDbi.setUpdatingTime(Timestamp.from(Instant.now()));
		return userDbi;
	}

	@Override
	public void execute(Users users) {
		for (User user : users.getUser()) {
			try {
				UserDbi userDbi = createUserDbi(user);
				userImportWriter.write(userDbi);
			} catch (Exception e) {
				LOGGER.error(String.format("Exception at execute import of data object: %s: ", user.toString()), e);
			}
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserImportWriter userImportWriter;

	@Required
	public void setUserImportWriter(IUserImportWriter userImportWriter) {
		this.userImportWriter = userImportWriter;
	}

}
