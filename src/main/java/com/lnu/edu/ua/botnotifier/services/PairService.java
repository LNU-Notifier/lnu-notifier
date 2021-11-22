package com.lnu.edu.ua.botnotifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.PairDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.IPairRepository;
import com.lnu.edu.ua.botnotifier.api.services.IPairService;

public class PairService implements IPairService {

	@Override
	public PairDbi findById(Integer id) {
		return pairRepository.findById(id).orElse(null);
	}

	@Override
	public List<PairDbi> findAllByUniqueGroupIdentifiers(String groupCode, String dayName, int pairNumber,
			String subgroupNumber, String weekType) {
		return pairRepository.findAllByUniqueGroupIdentifiers(groupCode, dayName, pairNumber, subgroupNumber, weekType);
	}

	@Override
	public PairDbi save(PairDbi pairDbi) {
		return pairRepository.save(pairDbi);
	}

	@Override
	public void updateAllByUniqueGroupIdentifiers(PairDbi pairDbi) {
		pairRepository.updateAllByUniqueGroupIdentifiers(pairDbi.getGroupCode(), pairDbi.getDayName(),
				pairDbi.getPairNumber(), pairDbi.getSubgroupNumber(), pairDbi.getWeekType(), pairDbi.getSubjectName(),
				pairDbi.getSubjectType(), pairDbi.getClassroom(), pairDbi.getTeacher(), pairDbi.getUpdatingTime());
	}

	@Override
	public List<PairDbi> findAllByGroupCodeAndDayNameAndWeekType(String groupCode, String dayName, String weekType) {
		return pairRepository.findAllByGroupCodeAndDayNameAndWeekType(groupCode, dayName, weekType);
	}

	// -------------------------------------WIRING-------------------------------------

	private IPairRepository pairRepository;

	@Autowired
	public void setPairRepository(IPairRepository pairRepository) {
		this.pairRepository = pairRepository;
	}

}
