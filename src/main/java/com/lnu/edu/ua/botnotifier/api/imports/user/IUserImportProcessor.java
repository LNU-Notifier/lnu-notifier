package com.lnu.edu.ua.botnotifier.api.imports.user;

import generated.imports.dataobjects.Users;

public interface IUserImportProcessor {

	void execute(Users users);

}
