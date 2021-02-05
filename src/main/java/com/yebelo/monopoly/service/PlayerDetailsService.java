package com.yebelo.monopoly.service;

import com.yebelo.monopoly.exception.GameTurnAlreadyTakenException;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.exception.MaximumPlayerCreatedException;
import com.yebelo.monopoly.exception.NotEnoughPlayerException;

public interface PlayerDetailsService {

	Object createNewPlayer(Integer gameId, String playerName) throws MaximumPlayerCreatedException, Exception, IdentityDoesNotExistsException;

	Object playMonopoly(String playerId, Integer gameId) throws 
		GameTurnAlreadyTakenException, Exception, IdentityDoesNotExistsException, NotEnoughPlayerException;


}
