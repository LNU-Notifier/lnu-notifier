package com.lnu.edu.ua.botnotifier.api.entities;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public int getPairNumber() {
		return pairNumber;
	}

	public void setPairNumber(int pairNumber) {
		this.pairNumber = pairNumber;
	}

	public String getSubgroupNumber() {
		return subgroupNumber;
	}

	public void setSubgroupNumber(String subgroupNumber) {
		this.subgroupNumber = subgroupNumber;
	}

	public String getWeekType() {
		return weekType;
	}

	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public TeacherDbi getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDbi teacher) {
		this.teacher = teacher;
	}

	public Timestamp getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(Timestamp updatingTime) {
		this.updatingTime = updatingTime;
	}

	@Override
	public String toString() {
		return "PairDbi [id=" + id + ", groupCode=" + groupCode + ", dayName=" + dayName + ", pairNumber=" + pairNumber
				+ ", subgroupNumber=" + subgroupNumber + ", weekType=" + weekType + ", subjectName=" + subjectName
				+ ", subjectType=" + subjectType + ", classroom=" + classroom + ", teacher=" + teacher
				+ ", updatingTime=" + updatingTime + "]";
	}

}
