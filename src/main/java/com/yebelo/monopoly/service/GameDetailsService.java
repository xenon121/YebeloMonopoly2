package com.yebelo.monopoly.service;

import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;

public interface GameDetailsService {

	Object getAllGameDetails() throws Exception;

	Object getGameDetails(Integer gameId) throws IdentityDoesNotExistsException;
	
}
