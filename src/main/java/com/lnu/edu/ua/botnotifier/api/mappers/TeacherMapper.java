package com.lnu.edu.ua.botnotifier.api.mappers;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

import generated.imports.dataobjects.Teacher;

public class TeacherMapper {

	public static TeacherDbi mapToDbi(Teacher teacher) {
		if(teacher == null) {
			return null;
		}
		TeacherDbi teacherDbi = new TeacherDbi();
		teacherDbi.setLastName(teacher.getLastName());
		teacherDbi.setFirstName(teacher.getFirstName());
		teacherDbi.setMiddleName(teacher.getMiddleName());
		teacherDbi.setAcademicStatus(teacher.getAcademicStatus());
		return teacherDbi;
	}

	public static Teacher mapFromDbi(TeacherDbi teacherDbi) {
		if(teacherDbi == null) {
			return null;
		}
		Teacher teacher = new Teacher();
		teacher.setLastName(teacherDbi.getLastName());
		teacher.setFirstName(teacherDbi.getFirstName());
		teacher.setMiddleName(teacherDbi.getMiddleName());
		teacher.setAcademicStatus(teacherDbi.getAcademicStatus());
		return teacher;
	}

}
