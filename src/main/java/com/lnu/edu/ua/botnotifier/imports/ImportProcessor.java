package com.lnu.edu.ua.botnotifier.imports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.constants.FileTypes;
import com.lnu.edu.ua.botnotifier.api.imports.IImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.IImportRouter;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Pair;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Timetable;
import com.opencsv.bean.CsvToBeanBuilder;

public class ImportProcessor implements IImportProcessor, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportProcessor.class);

	private static final char CSV_SEPARATOR = ';';

	private Unmarshaller unmarshaller;

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ImportRouter.availableСlasses);
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("Unknown exception: ", e);
		}
	}

	@Override
	public void execute(File file) {
		String fileType = FilenameUtils.getExtension(file.getName());
		Object object = null;
		switch (fileType) {
		case FileTypes.XML_TYPE: {
			try {
				object = unmarshaller.unmarshal(file);
				LOGGER.info("Successfully unmarshalled file: " + file.getName());
			} catch (JAXBException e) {
				LOGGER.error("Unmarshalling error:", e);
			}
			break;
		}
		case FileTypes.CSV_TYPE: {
			InputStream inputStream = null;
			InputStream bOMInputStream = null;
			Reader reader = null;
			try {
				inputStream = new FileInputStream(file);
				bOMInputStream = new BOMInputStream(inputStream);
				reader = new InputStreamReader(bOMInputStream, StandardCharsets.UTF_8);
				CsvToBeanBuilder<Pair> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
				csvToBeanBuilder.withSeparator(CSV_SEPARATOR);
				object = new Timetable(csvToBeanBuilder.withType(Pair.class).build().parse());
				LOGGER.info("Successfully parsed file: " + file.getName());
			} catch (IllegalStateException | FileNotFoundException e) {
				LOGGER.error("Csv parsing error:", e);
			} finally {
				IOUtils.closeQuietly(reader);
				IOUtils.closeQuietly(bOMInputStream);
				IOUtils.closeQuietly(inputStream);
			}
			break;
		}
		}
		try {
			importRouter.redirect(object);
		} catch (Exception e) {
			LOGGER.error("Unknown exception:", e);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IImportRouter importRouter;

	@Required
	public void setImportRouter(IImportRouter importRouter) {
		this.importRouter = importRouter;
	}

}
