package com.lnu.edu.ua.botnotifier.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

public interface IUserRepository extends JpaRepository<UserDbi, Integer> {

}
