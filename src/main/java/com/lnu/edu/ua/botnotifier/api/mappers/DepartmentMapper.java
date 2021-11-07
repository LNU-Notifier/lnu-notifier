package com.lnu.edu.ua.botnotifier.api.mappers;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;

import generated.imports.dataobjects.Department;

public class DepartmentMapper {

	public static DepartmentDbi mapToDbi(Department department) {
		DepartmentDbi departmentDbi = new DepartmentDbi();
		departmentDbi.setName(department.getName());
		return departmentDbi;
	}

	public static Department mapFromDbi(DepartmentDbi departmentDbi) {
		Department department = new Department();
		department.setName(departmentDbi.getName());
		return department;
	}

}
