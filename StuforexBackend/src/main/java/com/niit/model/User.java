package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "User_Detail")
public class User {

	@Id
	private String username ;
	
	@NotEmpty
	private String password ;
	
	@NotEmpty
	private String firstname ;
	
	private String lastname ;
	
	private String phonenumber ;
	
	@Column(unique = true , nullable = false)
	private String email ;
	
	private String role ;
	
	@Column(name = "Online_Status") 
	private boolean online ;

	
	// getter-setter :
	
	/* for username */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/* for password */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* for firstname */
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/* for lastname */
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/* for phonenumber */
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/* for email */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* for user_role */
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/* for online_status */
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}




}
