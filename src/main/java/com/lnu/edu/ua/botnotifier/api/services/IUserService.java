package com.lnu.edu.ua.botnotifier.api.services;

import java.util.List;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public interface IUserService {

	UserDbi findById(Integer id);

	List<UserDbi> findByUsername(String username);

	void save(UserDbi userDbi);
	
	void updateByUsername(UserDbi userDbi);

}
