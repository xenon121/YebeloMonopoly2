package com.yebelo.monopoly.exception;

public class IdentityDoesNotExistsException extends Throwable{

	public IdentityDoesNotExistsException() {
		super("Identity you trying to access does not exists...");
	}
	
	public IdentityDoesNotExistsException(String message) {
		super(message);
	}
}
