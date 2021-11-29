package com.lnu.edu.ua.botnotifier.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.IUserRepository;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;

public class UserService implements IUserService {

	@Override
	public UserDbi findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public UserDbi save(UserDbi userDbi) {
		return userRepository.save(userDbi);
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserRepository userRepository;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
