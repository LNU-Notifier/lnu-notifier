package com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects;

import com.opencsv.bean.CsvBindByName;

public class Pair {

	@CsvBindByName(column = "Group code")
	private String groupCode;

	@CsvBindByName(column = "Day name")
	private String dayName;

	@CsvBindByName(column = "Pair number")
	private int pairNumber;

	@CsvBindByName(column = "Subgroup number")
	private String subgroupNumber;

	@CsvBindByName(column = "Week type")
	private String weekType;

	@CsvBindByName(column = "Subject name")
	private String subjectName;

	@CsvBindByName(column = "Subject type")
	private String subjectType;

	@CsvBindByName(column = "Classroom")
	private String classroom;

	@CsvBindByName(column = "Teacher last name")
	private String teacherLastName;

	@CsvBindByName(column = "Teacher first name")
	private String teacherFirstName;

	@CsvBindByName(column = "Teacher middle name")
	private String teacherMiddleName;

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

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}

	public String getTeacherFirstName() {
		return teacherFirstName;
	}

	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}

	public String getTeacherMiddleName() {
		return teacherMiddleName;
	}

	public void setTeacherMiddleName(String teacherMiddleName) {
		this.teacherMiddleName = teacherMiddleName;
	}

	@Override
	public String toString() {
		return "Pair [groupCode=" + groupCode + ", dayName=" + dayName + ", pairNumber=" + pairNumber
				+ ", subgroupNumber=" + subgroupNumber + ", weekType=" + weekType + ", subjectName=" + subjectName
				+ ", subjectType=" + subjectType + ", classroom=" + classroom + ", teacherLastName=" + teacherLastName
				+ ", teacherFirstName=" + teacherFirstName + ", teacherMiddleName=" + teacherMiddleName + "]";
	}

}
