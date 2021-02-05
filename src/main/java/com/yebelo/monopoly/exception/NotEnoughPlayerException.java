package com.yebelo.monopoly.exception;

public class NotEnoughPlayerException extends Throwable{

	public NotEnoughPlayerException() {
		super("Two player needed to play game...");
		// TODO Auto-generated constructor stub
	}

	public NotEnoughPlayerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
