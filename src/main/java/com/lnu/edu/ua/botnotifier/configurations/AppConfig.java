package com.lnu.edu.ua.botnotifier.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportWriter;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.IDepartmentService;
import com.lnu.edu.ua.botnotifier.api.services.ITeacherService;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;
import com.lnu.edu.ua.botnotifier.imports.ImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.ImportRouter;
import com.lnu.edu.ua.botnotifier.imports.MasterDataImporter;
import com.lnu.edu.ua.botnotifier.imports.department.DepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.department.DepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.imports.teacher.TeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.teacher.TeacherImportWriter;
import com.lnu.edu.ua.botnotifier.imports.user.UserImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.user.UserImportWriter;
import com.lnu.edu.ua.botnotifier.services.DepartmentService;
import com.lnu.edu.ua.botnotifier.services.TeacherService;
import com.lnu.edu.ua.botnotifier.services.UserService;

@Configuration
public class AppConfig {

	@Bean
	public IDepartmentService departmentService() {
		return new DepartmentService();
	}

	@Bean
	public IUserService userService() {
		return new UserService();
	}

	@Bean
	public ITeacherService teacherService() {
		return new TeacherService();
	}

	@Bean
	public IUserImportWriter userImportWriter() {
		UserImportWriter userImportWriter = new UserImportWriter();
		userImportWriter.setUserService(userService());
		return userImportWriter;
	}

	@Bean
	public ITeacherImportWriter teacherImportWriter() {
		TeacherImportWriter teacherImportWriter = new TeacherImportWriter();
		teacherImportWriter.setTeacherService(teacherService());
		return teacherImportWriter;
	}

	@Bean
	public IDepartmentImportWriter departmentImportWriter() {
		DepartmentImportWriter departmentImportWriter = new DepartmentImportWriter();
		departmentImportWriter.setDepartmentService(departmentService());
		return departmentImportWriter;
	}

	@Bean
	public IUserImportProcessor userImportProcessor() {
		UserImportProcessor userImportProcessor = new UserImportProcessor();
		userImportProcessor.setUserImportWriter(userImportWriter());
		return userImportProcessor;
	}

	@Bean
	public ITeacherImportProcessor teacherImportProcessor() {
		TeacherImportProcessor teacherImportProcessor = new TeacherImportProcessor();
		teacherImportProcessor.setTeacherImportWriter(teacherImportWriter());
		teacherImportProcessor.setDepartmentService(departmentService());
		return teacherImportProcessor;
	}

	@Bean
	public IDepartmentImportProcessor departmentImportProcessor() {
		DepartmentImportProcessor departmentImportProcessor = new DepartmentImportProcessor();
		departmentImportProcessor.setDepartmentImportWriter(departmentImportWriter());
		return departmentImportProcessor;
	}

	@Bean
	public ImportRouter importRouter() {
		ImportRouter importRouter = new ImportRouter();
		importRouter.setUserImportProcessor(userImportProcessor());
		importRouter.setTeacherImportProcessor(teacherImportProcessor());
		importRouter.setDepartmentImportProcessor(departmentImportProcessor());
		return importRouter;
	}

	@Bean
	public ImportProcessor importProcessor() {
		ImportProcessor importProcessor = new ImportProcessor();
		importProcessor.setImportRouter(importRouter());
		return importProcessor;
	}

	@Bean
	public MasterDataImporter masterDataImporter() {
		MasterDataImporter masterDataImporter = new MasterDataImporter();
		masterDataImporter.setImportProcessor(importProcessor());
		return masterDataImporter;
	}
}
