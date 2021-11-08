package com.lnu.edu.ua.botnotifier.api.imports.timetable.dataObjects;

import java.util.List;

public class Timetable {

	private List<Pair> pairList;

	public Timetable() {
		// TODO Auto-generated constructor stub
	}

	public Timetable(List<Pair> pairList) {
		super();
		this.pairList = pairList;
	}

	public List<Pair> getPairList() {
		return pairList;
	}

	public void setPairList(List<Pair> pairList) {
		this.pairList = pairList;
	}

	@Override
	public String toString() {
		return "Timetable [pairList=" + pairList + "]";
	}

}
