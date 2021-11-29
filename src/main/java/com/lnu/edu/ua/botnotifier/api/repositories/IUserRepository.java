package com.lnu.edu.ua.botnotifier.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

@Transactional
public interface IUserRepository extends JpaRepository<UserDbi, Long> {

}
