package com.lnu.edu.ua.botnotifier.imports.department;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.api.mappers.DepartmentMapper;

import generated.imports.dataobjects.Department;
import generated.imports.dataobjects.Departments;

public class DepartmentImportProcessor implements IDepartmentImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentImportProcessor.class);

	private DepartmentDbi createDepartmentDbi(Department department) throws Exception {
		DepartmentDbi departmentDbi = DepartmentMapper.mapToDbi(department);
		departmentDbi.setUpdatingTime(Timestamp.from(Instant.now()));
		return departmentDbi;
	}

	@Override
	public void execute(Departments departments) {
		for (Department department : departments.getDepartment()) {
			try {
				DepartmentDbi departmentDbi = createDepartmentDbi(department);
				departmentImportWriter.write(departmentDbi);
			} catch (Exception e) {
				LOGGER.error(String.format("Exception at execute import of data object: %s: ", department.toString()),
						e);
			}
		}
	}

	// -------------------------------------WIRING-------------------------------------

	IDepartmentImportWriter departmentImportWriter;

	@Required
	public void setDepartmentImportWriter(IDepartmentImportWriter departmentImportWriter) {
		this.departmentImportWriter = departmentImportWriter;
	}

}
