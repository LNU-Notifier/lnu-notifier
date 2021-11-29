package com.lnu.edu.ua.botnotifier.api.mappers;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects.Pair;

public class PairMapper {

	public static PairDbi mapToDbi(Pair pair) {
		if(pair == null) {
			return null;
		}
		PairDbi pairDbi = new PairDbi();
		pairDbi.setGroupCode(pair.getGroupCode());
		pairDbi.setDayName(pair.getDayName());
		pairDbi.setPairNumber(pair.getPairNumber());
		pairDbi.setSubgroupNumber(pair.getSubgroupNumber());
		pairDbi.setWeekType(pair.getWeekType());
		pairDbi.setSubjectName(pair.getSubjectName());
		pairDbi.setSubjectType(pair.getSubjectType());
		pairDbi.setClassroom(pair.getClassroom());
		return pairDbi;
	}

	public static Pair mapFromDbi(PairDbi pairDbi) {
		if(pairDbi == null) {
			return null;
		}
		Pair pair = new Pair();
		pair.setGroupCode(pairDbi.getGroupCode());
		pair.setDayName(pairDbi.getDayName());
		pair.setPairNumber(pairDbi.getPairNumber());
		pair.setSubgroupNumber(pairDbi.getSubgroupNumber());
		pair.setWeekType(pairDbi.getWeekType());
		pair.setSubjectName(pairDbi.getSubjectName());
		pair.setSubjectType(pairDbi.getSubjectType());
		pair.setClassroom(pairDbi.getClassroom());
		return pair;
	}

}
