package com.yebelo.monopoly.exception;

public class GameSessionAlreadyExists extends Throwable {

	public GameSessionAlreadyExists(){
		super("Game session already ongoing. please finish current session first");
	}

	public GameSessionAlreadyExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}	
	
}
