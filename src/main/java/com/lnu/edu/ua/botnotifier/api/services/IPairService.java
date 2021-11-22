package com.lnu.edu.ua.botnotifier.api.services;

import java.util.List;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;

public interface IPairService {

	PairDbi findById(Integer id);

	List<PairDbi> findAllByUniqueGroupIdentifiers(String groupCode, String dayName, int pairNumber,
			String subgroupNumber, String weekType);

	PairDbi save(PairDbi pairDbi);

	void updateAllByUniqueGroupIdentifiers(PairDbi pairDbi);

	List<PairDbi> findAllByGroupCodeAndDayNameAndWeekType(String groupCode, String dayName, String weekType);

}
