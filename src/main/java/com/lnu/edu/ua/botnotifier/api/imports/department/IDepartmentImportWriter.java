package com.lnu.edu.ua.botnotifier.api.imports.department;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;

public interface IDepartmentImportWriter {
	
	void write(DepartmentDbi departmentDbi);

}
