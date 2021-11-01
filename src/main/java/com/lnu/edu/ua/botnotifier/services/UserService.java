package com.lnu.edu.ua.botnotifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.IUserRepository;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;

public class UserService implements IUserService {

	@Override
	public UserDbi findById(Integer id) {
		return null;
	}

	@Override
	public List<UserDbi> findByUsername(String username) {
		return null;
	}

	@Override
	public void save(UserDbi userDbi) {
		
	}

	@Override
	public void updateByUsername(UserDbi userDbi) {
		
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserRepository userRepository;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
