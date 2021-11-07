package com.lnu.edu.ua.botnotifier.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lnu.edu.ua.botnotifier.api.services.IUserService;
import com.lnu.edu.ua.botnotifier.imports.ImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.ImportRouter;
import com.lnu.edu.ua.botnotifier.imports.MasterDataImporter;
import com.lnu.edu.ua.botnotifier.imports.user.UserImportProcessor;
import com.lnu.edu.ua.botnotifier.imports.user.UserImportWriter;
import com.lnu.edu.ua.botnotifier.services.UserService;

@Configuration
public class AppConfig {

	@Bean
	public IUserService userService() {
		return new UserService();
	}

	@Bean
	public UserImportWriter userImportWriter() {
		UserImportWriter userImportWriter = new UserImportWriter();
		userImportWriter.setUserService(userService());
		return userImportWriter;
	}

	@Bean
	public UserImportProcessor userImportProcessor() {
		UserImportProcessor userImportProcessor = new UserImportProcessor();
		userImportProcessor.setUserImportWriter(userImportWriter());
		return userImportProcessor;
	}

	@Bean
	public ImportRouter importRouter() {
		ImportRouter importRouter = new ImportRouter();
		importRouter.setUserImportProcessor(userImportProcessor());
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
