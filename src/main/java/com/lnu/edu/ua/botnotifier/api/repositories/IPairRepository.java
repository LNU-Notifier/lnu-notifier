package com.lnu.edu.ua.botnotifier.api.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

@Transactional
public interface IPairRepository extends JpaRepository<PairDbi, Integer> {

	@Query(value = "FROM PairDbi WHERE groupCode = ?1 AND dayName = ?2 AND pairNumber = ?3 AND subgroupNumber = ?4 AND weekType = ?5")
	List<PairDbi> findAllByUniqueGroupIdentifiers(String groupCode, String dayName, int pairNumber,
			String subgroupNumber, String weekType);

	@Modifying
	@Query(value = "UPDATE PairDbi SET subjectName = ?6, subjectType = ?7, classroom = ?8, teacher = ?9, updatingTime = ?10 WHERE groupCode = ?1 AND dayName = ?2 AND pairNumber = ?3 AND subgroupNumber = ?4 AND weekType = ?5")
	void updateAllByUniqueGroupIdentifiers(String groupCode, String dayName, int pairNumber, String subgroupNumber,
			String weekType, String subjectName, String subjectType, String classroom, TeacherDbi teacher,
			Timestamp updatingTime);

	@Query(value = "FROM PairDbi WHERE groupCode = ?1 AND dayName = ?2 AND weekType in ('Всі', ?3) AND subgroupNumber in ('Всі', ?4)")
	List<PairDbi> findAllByUserData(String groupCode, String dayName, String weekType, String subgroupNumber);
}
