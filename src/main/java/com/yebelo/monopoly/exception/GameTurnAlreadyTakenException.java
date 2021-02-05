package com.yebelo.monopoly.exception;

public class GameTurnAlreadyTakenException extends Throwable{

	public GameTurnAlreadyTakenException() {
		super("Player has taken recent turn, please wait until your turn comes...");
		// TODO Auto-generated constructor stub
	}

	public GameTurnAlreadyTakenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
