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
@Table(name = "pairs")
public class PairDbi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String groupCode;
	@Column(nullable = false)
	private String dayName;
	@Column(nullable = false)
	private int pairNumber;
	@Column(nullable = false)
	private String subgroupNumber;
	@Column(nullable = false)
	private String weekType;
	@Column(nullable = false)
	private String subjectName;
	@Column(nullable = false)
	private String subjectType;
	@Column(nullable = false)
	private String classroom;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
	private TeacherDbi teacher;
	@Column(nullable = false)
	private Timestamp updatingTime;

}
