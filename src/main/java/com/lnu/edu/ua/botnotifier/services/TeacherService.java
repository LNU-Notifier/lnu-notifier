package com.lnu.edu.ua.botnotifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lnu.edu.ua.botnotifier.api.entities.TeacherDbi;
import com.lnu.edu.ua.botnotifier.api.repositories.ITeacherRepository;
import com.lnu.edu.ua.botnotifier.api.services.ITeacherService;

public class TeacherService implements ITeacherService {

	@Override
	public TeacherDbi findById(Integer id) {
		return teacherRepository.findById(id).orElse(null);
	}

	@Override
	public List<TeacherDbi> findByPIB(String lastName, String firstName, String middleName) {
		return teacherRepository.findByPIB(lastName, firstName, middleName);
	}

	@Override
	public TeacherDbi save(TeacherDbi teacherDbi) {
		return teacherRepository.save(teacherDbi);
	}

	@Override
	public void updateByPIB(TeacherDbi teacherDbi) {
		teacherRepository.updateByPIB(teacherDbi.getLastName(), teacherDbi.getFirstName(), teacherDbi.getMiddleName(),
				teacherDbi.getAcademicStatus(), teacherDbi.getDepartment(), teacherDbi.getUpdatingTime());
	}

	// -------------------------------------WIRING-------------------------------------

	private ITeacherRepository teacherRepository;

	@Autowired
	public void setTeacherRepository(ITeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

}
