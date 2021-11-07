package com.lnu.edu.ua.botnotifier.api.services;

import java.util.List;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

public interface IDepartmentService {

	DepartmentDbi findById(Integer id);

	List<DepartmentDbi> findAllByName(String name);

	DepartmentDbi save(DepartmentDbi departmentDbi);

	void updateAllByName(DepartmentDbi departmentDbi);

}
