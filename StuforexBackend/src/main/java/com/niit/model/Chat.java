package com.niit.model;

public class Chat {
	
	private String from ;
	private String to ;
	private String message ;
	
	
	// getter-setter method :
	
	/* for sender */
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	/* for receiver */
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	
	/* for messages exchanged */
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "Chat [message=" + message + " , to=" + to + "]";
	}


}
