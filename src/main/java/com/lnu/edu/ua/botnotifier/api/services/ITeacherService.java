package com.lnu.edu.ua.botnotifier.api.services;

import java.util.List;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

public interface ITeacherService {

	TeacherDbi findById(Integer id);

	List<TeacherDbi> findAllByPIB(String lastName, String firstName, String middleName);

	TeacherDbi save(TeacherDbi teacherDbi);

	void updateAllByPIB(TeacherDbi teacherDbi);

}
