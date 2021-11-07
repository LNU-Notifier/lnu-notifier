package com.lnu.edu.ua.botnotifier.imports;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.imports.IImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.IImportRouter;

public class ImportProcessor implements IImportProcessor, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportProcessor.class);

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
		try {
			Object object = unmarshaller.unmarshal(file);
			LOGGER.info("Successfully unmarshalled file: " + file.getName());
			importRouter.redirect(object);
		} catch (JAXBException e) {
			LOGGER.error("Unmarshalling error:", e);
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
