package com.lnu.edu.ua.botnotifier.api.imports.user;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public interface IUserImportWriter {

	void write(UserDbi userDbi);

}
