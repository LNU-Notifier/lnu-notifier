package com.lnu.edu.ua.botnotifier.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lnu.edu.ua.botnotifier.api.entities.UserDbi;

@Transactional
public interface IUserRepository extends JpaRepository<UserDbi, Integer> {

	@Query(value = "FROM UserDbi WHERE username = ?1")
	List<UserDbi> findAllByUsername(String username);

	@Modifying
	@Query(value = "UPDATE UserDbi SET password = ?2, email = ?3, updatingTime = ?4 WHERE username = ?1")
	void updateAllByUsername(String username, String password, String email, Timestamp updatingTime);

}
