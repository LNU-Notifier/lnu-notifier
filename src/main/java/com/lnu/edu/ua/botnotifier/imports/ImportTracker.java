package com.lnu.edu.ua.botnotifier.imports;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lnu.edu.ua.botnotifier.api.constants.FileTypes;
import com.lnu.edu.ua.botnotifier.api.imports.IImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.IImportTracker;

public class ImportTracker implements IImportTracker {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportTracker.class);

	private static final String[] EXTENSIONS_ARRAY = { FileTypes.XML_TYPE, FileTypes.CSV_TYPE };

	private IImportProcessor importProcessor;
	private File importDirectory;

	public ImportTracker(IImportProcessor importProcessor, File importDirectory) {
		this.importProcessor = importProcessor;
		this.importDirectory = importDirectory;
	}

	@Override
	public void run() {
		Collection<File> files = FileUtils.listFiles(importDirectory, EXTENSIONS_ARRAY, true);
		for (File file : files) {
			LOGGER.info("Detected file to import: " + file.getName());
			importProcessor.execute(file);
			file.delete();
			LOGGER.info("Completed import of file: " + file.getName());
		}
	}

}
