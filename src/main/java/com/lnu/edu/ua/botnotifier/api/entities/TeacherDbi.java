package com.lnu.edu.ua.botnotifier.api.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class TeacherDbi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String middleName;
	@Column(nullable = false)
	private String academicStatus;
	@Column(nullable = false)
	private String department;
	@Column(nullable = false)
	private Timestamp updatingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getAcademicStatus() {
		return academicStatus;
	}

	public void setAcademicStatus(String academicStatus) {
		this.academicStatus = academicStatus;
	}

	public Timestamp getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(Timestamp updatingTime) {
		this.updatingTime = updatingTime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "TeacherDbi [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName="
				+ middleName + ", academicStatus=" + academicStatus + ", department=" + department + ", updatingTime="
				+ updatingTime + "]";
	}

}
