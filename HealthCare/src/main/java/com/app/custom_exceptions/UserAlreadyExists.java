package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExists extends RuntimeException {
	public UserAlreadyExists(String mesg) {
		super(mesg);
	}
}
