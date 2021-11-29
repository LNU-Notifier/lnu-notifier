package com.lnu.edu.ua.botnotifier.api.dataobjects;

public class User {
	
	private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String groupCode;
    private String subGroup;
    private String typeOfWeek;
    private String weekDay;
    
    
    public User() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public String getTypeOfWeek() {
		return typeOfWeek;
	}

	public void setTypeOfWeek(String typeOfWeek) {
		this.typeOfWeek = typeOfWeek;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", groupCode=" + groupCode + ", subGroup=" + subGroup + ", typeOfWeek=" + typeOfWeek + ", weekDay="
				+ weekDay + "]";
	}
    
}
