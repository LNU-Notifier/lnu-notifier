package com.lnu.edu.ua.botnotifier.imports.teacher;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportWriter;
import com.lnu.edu.ua.botnotifier.api.mappers.TeacherMapper;
import com.lnu.edu.ua.botnotifier.api.services.IDepartmentService;

import generated.imports.dataobjects.Teacher;
import generated.imports.dataobjects.Teachers;

public class TeacherImportProcessor implements ITeacherImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherImportProcessor.class);

	private TeacherDbi createTeacherDbi(Teacher teacher) throws Exception {
		TeacherDbi teacherDbi = TeacherMapper.mapToDbi(teacher);
		List<DepartmentDbi> departmentDbiList = departmentService.findAllByName(teacher.getDepartmentName());
		if (departmentDbiList.size() == 1) {
			teacherDbi.setDepartment(departmentDbiList.get(0));
		} else if (departmentDbiList.size() == 0) {
			throw new Exception(String.format("Department with name \"%s\", not found!", teacher.getDepartmentName()));
		} else {
			throw new Exception(
					String.format("Found several departments with name \"%s\"!", teacher.getDepartmentName()));
		}
		teacherDbi.setUpdatingTime(Timestamp.from(Instant.now()));
		return teacherDbi;
	}

	@Override
	public void execute(Teachers teachers) {
		for (Teacher teacher : teachers.getTeacher()) {
			try {
				TeacherDbi teacherDbi = createTeacherDbi(teacher);
				teacherImportWriter.write(teacherDbi);
			} catch (Exception e) {
				LOGGER.error(String.format("Exception at execute import of data object: %s: ", teacher.toString()), e);
			}
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private ITeacherImportWriter teacherImportWriter;

	@Required
	public void setTeacherImportWriter(ITeacherImportWriter teacherImportWriter) {
		this.teacherImportWriter = teacherImportWriter;
	}

	private IDepartmentService departmentService;

	@Required
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
