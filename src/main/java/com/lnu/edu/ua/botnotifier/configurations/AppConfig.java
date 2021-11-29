package com.lnu.edu.ua.botnotifier.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.department.IDepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportWriter;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.teacher.ITeacherImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.IDepartmentService;
import com.lnu.edu.ua.botnotifier.api.services.IPairService;
import com.lnu.edu.ua.botnotifier.api.services.ITeacherService;
import com.lnu.edu.ua.botnotifier.api.services.IUserService;
import com.lnu.edu.ua.botnotifier.imports.ImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.ImportRouter;
import com.lnu.edu.ua.botnotifier.imports.MasterDataImporter;
import com.lnu.edu.ua.botnotifier.imports.department.DepartmentImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.department.DepartmentImportWriter;
import com.lnu.edu.ua.botnotifier.imports.pair.PairImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.pair.PairImportWriter;
import com.lnu.edu.ua.botnotifier.imports.teacher.TeacherImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.teacher.TeacherImportWriter;
import com.lnu.edu.ua.botnotifier.services.DepartmentService;
import com.lnu.edu.ua.botnotifier.services.PairService;
import com.lnu.edu.ua.botnotifier.services.TeacherService;
import com.lnu.edu.ua.botnotifier.services.UserService;

@Configuration
public class AppConfig {

	@Bean
	public IUserService userService() {
		return new UserService();
	}

	@Bean
	public IDepartmentService departmentService() {
		return new DepartmentService();
	}

	@Bean
	public ITeacherService teacherService() {
		return new TeacherService();
	}

	@Bean
	public IPairService pairService() {
		return new PairService();
	}

	@Bean
	public IDepartmentImportWriter departmentImportWriter() {
		DepartmentImportWriter departmentImportWriter = new DepartmentImportWriter();
		departmentImportWriter.setDepartmentService(departmentService());
		return departmentImportWriter;
	}

	@Bean
	public ITeacherImportWriter teacherImportWriter() {
		TeacherImportWriter teacherImportWriter = new TeacherImportWriter();
		teacherImportWriter.setTeacherService(teacherService());
		return teacherImportWriter;
	}

	@Bean
	public IPairImportWriter pairImportWriter() {
		PairImportWriter pairImportWriter = new PairImportWriter();
		pairImportWriter.setPairService(pairService());
		return pairImportWriter;
	}

	@Bean
	public IDepartmentImportProcessor departmentImportProcessor() {
		DepartmentImportProcessor departmentImportProcessor = new DepartmentImportProcessor();
		departmentImportProcessor.setDepartmentImportWriter(departmentImportWriter());
		return departmentImportProcessor;
	}

	@Bean
	public ITeacherImportProcessor teacherImportProcessor() {
		TeacherImportProcessor teacherImportProcessor = new TeacherImportProcessor();
		teacherImportProcessor.setTeacherImportWriter(teacherImportWriter());
		teacherImportProcessor.setDepartmentService(departmentService());
		return teacherImportProcessor;
	}

	@Bean
	public IPairImportProcessor pairImportProcessor() {
		PairImportProcessor pairImportProcessor = new PairImportProcessor();
		pairImportProcessor.setPairImportWriter(pairImportWriter());
		pairImportProcessor.setTeacherService(teacherService());
		return pairImportProcessor;
	}

	@Bean
	public ImportRouter importRouter() {
		ImportRouter importRouter = new ImportRouter();
		importRouter.setDepartmentImportProcessor(departmentImportProcessor());
		importRouter.setTeacherImportProcessor(teacherImportProcessor());
		importRouter.setPairImportProcessor(pairImportProcessor());
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
