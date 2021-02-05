package com.yebelo.monopoly.service;

import com.yebelo.monopoly.exception.GameSessionAlreadyExists;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;

public interface GameDetailsService {

	Object getAllGameDetails() throws Exception;

	Object getGameDetails(Integer gameId) throws IdentityDoesNotExistsException;

	Object getGameDetailsByPlayerId(String playerId) throws IdentityDoesNotExistsException;

	Object createNewGame() throws GameSessionAlreadyExists, Exception;

	Object finishGame() throws Exception;
	
}
