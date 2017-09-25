package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ProfilePicture_Detail")
public class ProfilePicture {

	@Id
	private String username ;
	
	@Lob
	private byte[] image ;

	
	
	// getter-setter :
	
	
	/* for username */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	/* for display image */
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
