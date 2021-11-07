package com.lnu.edu.ua.botnotifier.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

@Transactional
public interface ITeacherRepository extends JpaRepository<TeacherDbi, Integer> {

	@Query(value = "FROM TeacherDbi WHERE lastName = ?1 AND firstName = ?2 AND middleName = ?3")
	List<TeacherDbi> findByPIB(String lastName, String firstName, String middleName);

	@Modifying
	@Query(value = "UPDATE TeacherDbi SET academicStatus = ?4, department = ?5, updatingTime = ?6 WHERE lastName = ?1 AND firstName = ?2 AND middleName = ?3")
	void updateByPIB(String lastName, String firstName, String middleName, String academicStatus, String department,
			Timestamp updatingTime);

}
