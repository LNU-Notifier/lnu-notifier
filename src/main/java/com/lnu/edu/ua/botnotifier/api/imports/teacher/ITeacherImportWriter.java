package com.lnu.edu.ua.botnotifier.api.imports.teacher;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

public interface ITeacherImportWriter {

	void write(TeacherDbi teacherDbi);

}
