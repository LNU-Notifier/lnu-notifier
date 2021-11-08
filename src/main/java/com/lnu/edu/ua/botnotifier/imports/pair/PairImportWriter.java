package com.lnu.edu.ua.botnotifier.imports.pair;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportWriter;
import com.lnu.edu.ua.botnotifier.api.services.IPairService;

public class PairImportWriter implements IPairImportWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PairImportWriter.class);

	@Override
	public void write(PairDbi pairDbi) {
		try {
			List<PairDbi> pairDbiList = pairService.findAllByUniqueGroupIdentifiers(pairDbi.getGroupCode(),
					pairDbi.getDayName(), pairDbi.getPairNumber(), pairDbi.getSubgroupNumber(), pairDbi.getWeekType());
			if (pairDbiList.size() == 0) {
				pairDbi = pairService.save(pairDbi);
				LOGGER.info("[I] " + pairDbi.toString());
			} else {
				pairService.updateAllByUniqueGroupIdentifiers(pairDbi);
				LOGGER.info("[U] " + pairDbi.toString());
			}
		} catch (Exception e) {
			LOGGER.error("Write exception: ", e);
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IPairService pairService;

	@Required
	public void setPairService(IPairService pairService) {
		this.pairService = pairService;
	}

}
