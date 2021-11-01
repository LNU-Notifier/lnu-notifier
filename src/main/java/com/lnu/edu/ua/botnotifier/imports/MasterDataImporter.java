package com.lnu.edu.ua.botnotifier.imports;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.imports.IImportProcessor;

public class MasterDataImporter implements InitializingBean, DisposableBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterDataImporter.class);

	private static final String IMPORT_DIRECTORY_PROPERTY_KEY = "import.directory";

	private ScheduledFuture<?> importTrackerFuture;

	@Override
	public void afterPropertiesSet() {
		try {
			String importDirectoryPropValue = System.getProperty(IMPORT_DIRECTORY_PROPERTY_KEY, null);
			File importDirectory = Paths.get(importDirectoryPropValue).toFile();
			if (!importDirectory.isDirectory()) {
				throw new NotDirectoryException(importDirectory.getAbsolutePath());
			}
			LOGGER.info("Import directory path: " + importDirectory.getAbsolutePath());
			ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
			ImportTracker importTracker = new ImportTracker(importProcessor, importDirectory);
			importTrackerFuture = scheduledExecutorService.scheduleWithFixedDelay(importTracker, 1000, 1000,
					TimeUnit.MILLISECONDS);
			LOGGER.info("Import tracker started!");
		} catch (InvalidPathException e) {
			LOGGER.error("Invalid path set! " + e.getMessage(), e);
		} catch (NullPointerException e) {
			LOGGER.error("Mandatory property \"" + IMPORT_DIRECTORY_PROPERTY_KEY + "\" is not set!", e);
		} catch (NotDirectoryException e) {
			LOGGER.error("Path \"" + e.getFile() + "\" is not a directory!");
		}
	}

	@Override
	public void destroy() throws Exception {
		importTrackerFuture.cancel(false);
	}

	// -------------------------------------WIRING-------------------------------------

	private IImportProcessor importProcessor;

	@Required
	public void setImportProcessor(IImportProcessor importProcessor) {
		this.importProcessor = importProcessor;
	}
}
