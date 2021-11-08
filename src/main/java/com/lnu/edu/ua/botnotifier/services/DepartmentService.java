package com.lnu.edu.ua.botnotifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.DepartmentDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.IDepartmentRepository;
import com.lnu.edu.ua.botnotifier.api.services.IDepartmentService;

public class DepartmentService implements IDepartmentService {

	@Override
	public DepartmentDbi findById(Integer id) {
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<DepartmentDbi> findAllByName(String name) {
		return departmentRepository.findAllByName(name);
	}

	@Override
	public DepartmentDbi save(DepartmentDbi departmentDbi) {
		return departmentRepository.save(departmentDbi);
	}

	@Override
	public void updateAllByName(DepartmentDbi departmentDbi) {
		departmentRepository.updateAllByName(departmentDbi.getName(), departmentDbi.getUpdatingTime());

	}

	// -------------------------------------WIRING-------------------------------------

	private IDepartmentRepository departmentRepository;

	@Autowired
	public void setDepartmentRepository(IDepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

}
