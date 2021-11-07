package com.lnu.edu.ua.botnotifier.api.entities;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDbi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private Timestamp updatingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(Timestamp updatingTime) {
		this.updatingTime = updatingTime;
	}

	@Override
	public String toString() {
		return "UserDbi [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", updatingTime=" + updatingTime + "]";
	}

}
