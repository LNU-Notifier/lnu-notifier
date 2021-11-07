package com.lnu.edu.ua.botnotifier.imports.teacher;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.ITeacherService;

public class TeacherImportWriter implements ITeacherImportWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherImportWriter.class);

	@Override
	public void write(TeacherDbi teacherDbi) {
		try {
			List<TeacherDbi> teachers = teacherService.findByPIB(teacherDbi.getLastName(), teacherDbi.getFirstName(),
					teacherDbi.getMiddleName());
			if (teachers.size() == 0) {
				teacherDbi = teacherService.save(teacherDbi);
				LOGGER.info("[I] " + teacherDbi.toString());
			} else {
				teacherService.updateByPIB(teacherDbi);
				LOGGER.info("[U] " + teacherDbi.toString());
			}
		} catch (Exception e) {
			LOGGER.error("Write exception: ", e);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private ITeacherService teacherService;

	@Required
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}

}
