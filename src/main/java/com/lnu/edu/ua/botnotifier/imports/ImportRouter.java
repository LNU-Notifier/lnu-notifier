package com.lnu.edu.ua.botnotifier.imports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.imports.IImportRouter;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Timetable;

import generated.imports.dataobjects.Departments;
import generated.imports.dataobjects.Teachers;

public class ImportRouter implements IImportRouter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportRouter.class);

	public static Class[] availableСlasses = { Teachers.class, Departments.class };

	@Override
	public void redirect(Object object) {
		if (object != null) {
			if (object instanceof Departments) {
				LOGGER.info("Data object redirect to: " + departmentImportProcessor.getClass().getSimpleName());
				departmentImportProcessor.execute((Departments) object);
			} else if (object instanceof Teachers) {
				LOGGER.info("Data object redirect to: " + teacherImportProcessor.getClass().getSimpleName());
				teacherImportProcessor.execute((Teachers) object);
			} else if (object instanceof Timetable) {
				LOGGER.info("Data object redirect to: " + pairImportProcessor.getClass().getSimpleName());
				pairImportProcessor.execute((Timetable) object);
			} else {
				LOGGER.error("Import processor is not available for this data type: " + object.getClass().getName());
			}
		} else {
			LOGGER.error("Input object to import is null!");
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private ITeacherImportProcessor teacherImportProcessor;

	@Required
	public void setTeacherImportProcessor(ITeacherImportProcessor teacherImportProcessor) {
		this.teacherImportProcessor = teacherImportProcessor;
	}

	private IDepartmentImportProcessor departmentImportProcessor;

	@Required
	public void setDepartmentImportProcessor(IDepartmentImportProcessor departmentImportProcessor) {
		this.departmentImportProcessor = departmentImportProcessor;
	}

	private IPairImportProcessor pairImportProcessor;

	@Required
	public void setPairImportProcessor(IPairImportProcessor pairImportProcessor) {
		this.pairImportProcessor = pairImportProcessor;
	}

}
