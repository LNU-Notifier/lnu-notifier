package com.lnu.edu.ua.botnotifier.imports.pair;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportProcessor;
import com.lnu.edu.ua.botnotifier.api.imports.pair.IPairImportWriter;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Pair;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Timetable;
import com.lnu.edu.ua.botnotifier.api.mappers.PairMapper;
import com.lnu.edu.ua.botnotifier.api.services.ITeacherService;

public class PairImportProcessor implements IPairImportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(PairImportProcessor.class);

	private PairDbi createPairDbi(Pair pair) throws Exception {
		PairDbi pairDbi = PairMapper.mapToDbi(pair);
		List<TeacherDbi> teacherDbiList = teacherService.findAllByPIB(pair.getTeacherLastName(),
				pair.getTeacherFirstName(), pair.getTeacherMiddleName());
		if (teacherDbiList.size() == 1) {
			pairDbi.setTeacher(teacherDbiList.get(0));
		} else if (teacherDbiList.size() == 0) {
			throw new Exception(String.format("Teacher with PIB \"%s %s %s\", not found!", pair.getTeacherLastName(),
					pair.getTeacherFirstName(), pair.getTeacherMiddleName()));
		} else {
			throw new Exception(String.format("Found several teachers with PIB \"%s %s %s\"!",
					pair.getTeacherLastName(), pair.getTeacherFirstName(), pair.getTeacherMiddleName()));
		}
		pairDbi.setUpdatingTime(Timestamp.from(Instant.now()));
		return pairDbi;
	}

	@Override
	public void execute(Timetable timetable) {
		List<Pair> pairList = timetable.getPairList();
		for (Pair pair : pairList) {
			try {
				PairDbi pairDbi = createPairDbi(pair);
				pairImportWriter.write(pairDbi);
			} catch (Exception e) {
				LOGGER.error(String.format("Exception at execute import of data object: %s: ", pair.toString()), e);
			}
		}
	}

	// -------------------------------------WIRING-------------------------------------

	private IPairImportWriter pairImportWriter;

	@Required
	public void setPairImportWriter(IPairImportWriter pairImportWriter) {
		this.pairImportWriter = pairImportWriter;
	}

	private ITeacherService teacherService;

	@Required
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}

}
