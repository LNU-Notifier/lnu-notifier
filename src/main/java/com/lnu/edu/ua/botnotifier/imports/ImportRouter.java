package com.lnu.edu.ua.botnotifier.imports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.imports.IImportRouter;
import com.lnu.edu.ua.botnotifier.api.imports.user.IUserImportProcessor;

import generated.imports.dataobjects.Users;

public class ImportRouter implements IImportRouter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportRouter.class);

	public static Class[] availableСlasses = { Users.class };

	@Override
	public void redirect(Object object) {
		if (object != null) {
			if (object instanceof Users) {
				LOGGER.info("Data object redirect to: " + userImportProcessor.getClass().getSimpleName());
				userImportProcessor.execute((Users) object);
			} else {
				LOGGER.error("Import processor is not available for this data type: " + object.getClass().getName());
			}
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IUserImportProcessor userImportProcessor;

	@Required
	public void setUserImportProcessor(IUserImportProcessor userImportProcessor) {
		this.userImportProcessor = userImportProcessor;
	}

}
