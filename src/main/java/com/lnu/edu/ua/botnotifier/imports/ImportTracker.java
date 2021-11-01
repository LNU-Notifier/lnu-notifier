package com.lnu.edu.ua.botnotifier.imports;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lnu.edu.ua.botnotifier.api.imports.IImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.IImportTracker;

public class ImportTracker implements IImportTracker {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportTracker.class);

	private static final String XML_TYPE = "xml";

	private File importDirectory;

	private IImportProcessor importProcessor;

	public ImportTracker(IImportProcessor importProcessor, File importDirectory) {
		this.importProcessor = importProcessor;
		this.importDirectory = importDirectory;
	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	@Override
	public void run() {
		File[] files = importDirectory.listFiles();
		for (File file : files) {
			if (XML_TYPE.equals(getFileExtension(file))) {
				LOGGER.info("File to import: " + file.getName());
				importProcessor.execute(file);
				file.delete();
			}
		}
	}

}
