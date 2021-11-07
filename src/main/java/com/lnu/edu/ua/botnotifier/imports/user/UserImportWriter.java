package com.lnu.edu.ua.botnotifier.imports.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;

public class UserImportWriter implements IUserImportWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserImportWriter.class);

	@Override
	public void write(UserDbi userDbi) {
		try {
			List<UserDbi> users = userService.findByUsername(userDbi.getUsername());
			if (users.size() == 0) {
				userService.save(userDbi);
				LOGGER.info("[I] " + userDbi.toString());
			} else {
				userService.updateByUsername(userDbi);
				LOGGER.info("[U] " + userDbi.toString());
			}
		} catch (Exception e) {
			LOGGER.error("Write exception: ", e);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserService userService;

	@Required
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
