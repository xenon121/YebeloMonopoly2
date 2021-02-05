package com.yebelo.monopoly.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yebelo.monopoly.constants.GameStatus;
import com.yebelo.monopoly.entity.GameDetails;
import com.yebelo.monopoly.exception.GameSessionAlreadyExists;
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

	@Override
	public Object getGameDetailsByPlayerId(String playerId) throws IdentityDoesNotExistsException {
		
		logger.debug("fetching game details by player id...");
		
		Optional<GameDetails> gameDetails = gameDetailsRepository.findByPlayersPlayerId(playerId);
		
		if(!gameDetails.isPresent()) {
			throw new IdentityDoesNotExistsException();
		}

		return gameDetails;	
	}

	@Override
	public Object createNewGame() throws GameSessionAlreadyExists, Exception {
		
		logger.debug("creating new game...");
		
		if(gameDetailsRepository.countByGameStatus(GameStatus.ONGOING) > 0) {
			throw new GameSessionAlreadyExists();
		}
		
		GameDetails gameDetails = new GameDetails();
		
		gameDetails.setStartTime(new Date());
		gameDetails.setGameStatus(GameStatus.ONGOING);
		
		return gameDetailsRepository.save(gameDetails);
	}

	@Override
	public Object finishGame() throws Exception {
		
		logger.debug("forcing existing game to finish...");
		
		List<GameDetails> gameDetailList = gameDetailsRepository.findByGameStatus(GameStatus.ONGOING);
		
		if(gameDetailList != null) {
			
			gameDetailList.forEach(gameDetails -> {
				gameDetails.setEndTime(new Date());
				gameDetails.setGameStatus(GameStatus.FINISHED);
			});	
			
			gameDetailsRepository.saveAll(gameDetailList);
		}
		
		return "All existing ongoing games has been finished successfully...";
	}

}
