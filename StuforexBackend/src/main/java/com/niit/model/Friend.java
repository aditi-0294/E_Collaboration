package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friend_Detail")
public class Friend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	
	private String fromId ;
	
	private String toId ;
	
	private char status ; // status = initially pending then accepted or denied

	
	
	// getter-setter :
	
	
	/* for friend_id */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	/* for sender - loggedIn user */
	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	
	/* for receiver - users other than loggedIn user */
	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	
	/* for request status - initially pending then accepted or denied */
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	

}
