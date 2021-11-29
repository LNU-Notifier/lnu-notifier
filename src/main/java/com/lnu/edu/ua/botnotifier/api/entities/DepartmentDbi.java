package com.lnu.edu.ua.botnotifier.api.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentDbi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Timestamp updatingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(Timestamp updatingTime) {
		this.updatingTime = updatingTime;
	}

	@Override
	public String toString() {
		return "DepartmentDbi [id=" + id + ", name=" + name + ", updatingTime=" + updatingTime + "]";
	}

}
