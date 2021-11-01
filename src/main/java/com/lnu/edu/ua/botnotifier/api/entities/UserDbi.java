package com.lnu.edu.ua.botnotifier.api.entities;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDbi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Timestamp registrationTime;

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
    
    public Timestamp getRegistrationTime() {
		return registrationTime;
	}
    
    public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Override
	public String toString() {
		return "UserDbi [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", registrationTime=" + registrationTime + "]";
	}
    
    

    
}
