package com.lnu.edu.ua.botnotifier.imports.teacher;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportWriter;
import com.lnu.edu.ua.botnotifier.api.mappers.TeacherMapper;

import generated.imports.dataobjects.Teacher;
import generated.imports.dataobjects.Teachers;

public class TeacherImportProcessor implements ITeacherImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherImportProcessor.class);

	@Override
	public void execute(Teachers teachers) {
		for (Teacher teacher : teachers.getTeacher()) {
			TeacherDbi teacherDbi = TeacherMapper.mapToDbi(teacher);
			teacherDbi.setUpdatingTime(Timestamp.from(Instant.now()));
			teacherImportWriter.write(teacherDbi);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private ITeacherImportWriter teacherImportWriter;

	@Required
	public void setTeacherImportWriter(ITeacherImportWriter teacherImportWriter) {
		this.teacherImportWriter = teacherImportWriter;
	}

}
