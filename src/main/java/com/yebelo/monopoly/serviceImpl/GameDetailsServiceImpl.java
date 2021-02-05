package com.yebelo.monopoly.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yebelo.monopoly.entity.GameDetails;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.repo.GameDetailsRepository;
import com.yebelo.monopoly.service.GameDetailsService;

@Service
public class GameDetailsServiceImpl implements GameDetailsService {

	@Autowired
	private GameDetailsRepository gameDetailsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(GameDetailsServiceImpl.class);
	
	@Override
	public Object getAllGameDetails() throws Exception {
		
		logger.debug("fetch all game details...");
		return gameDetailsRepository.findAll();
	}

	@Override
	public Object getGameDetails(Integer gameId) throws IdentityDoesNotExistsException {
		
		logger.debug("fetching game details by given identity...");
		
		Optional<GameDetails> gameDetails = gameDetailsRepository.findById(gameId);
			
		if(!gameDetails.isPresent()) {
			throw new IdentityDoesNotExistsException();
		}

		return gameDetails;	
	}

}
