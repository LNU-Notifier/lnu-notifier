package com.lnu.edu.ua.botnotifier.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
	private DepartmentDbi department;
	@Column(nullable = false)
	private Timestamp updatingTime;


}
