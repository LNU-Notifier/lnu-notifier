package com.lnu.edu.ua.botnotifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.IUserRepository;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;

public class UserService implements IUserService {

	@Override
	public UserDbi findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<UserDbi> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(UserDbi userDbi) {
		userRepository.save(userDbi);
	}

	@Override
	public void updateByUsername(UserDbi userDbi) {
		userRepository.updateByUsername(userDbi.getUsername(), userDbi.getPassword(), userDbi.getEmail());
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserRepository userRepository;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
