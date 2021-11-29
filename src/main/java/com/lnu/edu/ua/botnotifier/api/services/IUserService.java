package com.lnu.edu.ua.botnotifier.api.services;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public interface IUserService {

	UserDbi findById(long id);

	UserDbi save(UserDbi userDbi);

}
