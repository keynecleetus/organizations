package com.revature.organizations.dto;

public class Message {
	private String messageInfo;

	public String getMessage() {
		return messageInfo;
	}

	public void setMessage(String message) {
		this.messageInfo = message;
	}

	public Message(String message) {
		super();
		this.setMessage(message);
	}


}
