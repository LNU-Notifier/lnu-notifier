package com.lnu.edu.ua.botnotifier.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

@Transactional
public interface IDepartmentRepository extends JpaRepository<DepartmentDbi, Integer> {

	@Query(value = "FROM DepartmentDbi WHERE name = ?1")
	List<DepartmentDbi> findAllByName(String name);

	@Modifying
	@Query(value = "UPDATE DepartmentDbi SET updatingTime = ?2 WHERE name = ?1")
	void updateAllByName(String name, Timestamp updatingTime);

}
