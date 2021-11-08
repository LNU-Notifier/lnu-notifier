package com.lnu.edu.ua.botnotifier.imports.department;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.IDepartmentService;

public class DepartmentImportWriter implements IDepartmentImportWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentImportWriter.class);

	@Override
	public void write(DepartmentDbi departmentDbi) {
		try {
			List<DepartmentDbi> departmentDbiList = departmentService.findAllByName(departmentDbi.getName());
			if (departmentDbiList.size() == 0) {
				departmentDbi = departmentService.save(departmentDbi);
				LOGGER.info("[I] " + departmentDbi.toString());
			} else {
				departmentService.updateAllByName(departmentDbi);
				LOGGER.info("[U] " + departmentDbi.toString());
			}
		} catch (Exception e) {
			LOGGER.error("Write exception: ", e);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IDepartmentService departmentService;

	@Required
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
