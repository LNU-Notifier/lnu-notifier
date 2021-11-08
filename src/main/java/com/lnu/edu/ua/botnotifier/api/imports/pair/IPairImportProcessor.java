package com.lnu.edu.ua.botnotifier.api.imports.pair;

import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Timetable;

public interface IPairImportProcessor {

	void execute(Timetable timetable);

}
