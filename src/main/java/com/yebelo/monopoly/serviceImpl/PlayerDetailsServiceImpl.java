package com.yebelo.monopoly.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yebelo.monopoly.constants.ActivityStatus;
import com.yebelo.monopoly.constants.GameStatus;
import com.yebelo.monopoly.entity.GameActivity;
import com.yebelo.monopoly.entity.GameDetails;
import com.yebelo.monopoly.entity.PlaceDetails;
import com.yebelo.monopoly.entity.PlayerDetails;
import com.yebelo.monopoly.exception.GameTurnAlreadyTakenException;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.exception.MaximumPlayerCreatedException;
import com.yebelo.monopoly.exception.NotEnoughPlayerException;
import com.yebelo.monopoly.repo.GameActivityRepo;
import com.yebelo.monopoly.repo.GameDetailsRepository;
import com.yebelo.monopoly.repo.PlaceDetailsRepo;
import com.yebelo.monopoly.repo.PlayerDetailsRepo;
import com.yebelo.monopoly.service.PlayerDetailsService;

@Service
public class PlayerDetailsServiceImpl implements PlayerDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(PlayerDetailsServiceImpl.class);
	
	@Autowired
	private PlayerDetailsRepo playerDetailsRepo;
	
	@Autowired
	private GameDetailsRepository gameDetailsRepository;
	
	@Autowired
	private PlaceDetailsRepo placeDetailsRepo;
	
	@Autowired
	private GameActivityRepo gameActivityRepo;
	
	@Override
	public Object createNewPlayer(Integer gameId, String playerName) throws MaximumPlayerCreatedException, Exception, IdentityDoesNotExistsException {
		
		logger.debug("creating new player...");
		
		Optional<GameDetails> gameDetails =  gameDetailsRepository.findByGameIdAndGameStatus(gameId, GameStatus.ONGOING);
		
		if(!gameDetails.isPresent()) {
			throw new IdentityDoesNotExistsException();
		}
		
		if(gameDetails.get().getPlayers().size() >= 2) {
			throw new MaximumPlayerCreatedException();
		}
		
		PlayerDetails playerDetails = new PlayerDetails();
				
		playerDetails.setPlayerName(playerName);
		playerDetails.setCurrentTurn(true);
		playerDetails.setBalance(1000);
		
		playerDetails = playerDetailsRepo.save(playerDetails);
		
		gameDetails.get().getPlayers().addAll(Arrays.asList(playerDetails));
		
		gameDetailsRepository.save(gameDetails.get());
		
		return playerDetails;
	}

	@Transactional
	@Override
	public Object playMonopoly(String playerId, Integer gameId)
			throws GameTurnAlreadyTakenException, Exception, IdentityDoesNotExistsException, NotEnoughPlayerException {
		
		logger.debug("Playing monopoly...");
		
		Optional<GameDetails> gameDetails =  gameDetailsRepository.findByGameIdAndGameStatus(gameId, GameStatus.ONGOING);
		
		if(!gameDetails.isPresent()) {
			throw new IdentityDoesNotExistsException("no active game session with given game id");
		}
		
		if(gameDetails.get().getPlayers().size() != 2) {
			throw new NotEnoughPlayerException();
		}
		
		Optional<PlayerDetails> playerDetails = playerDetailsRepo.findById(playerId);
		
		if(!playerDetails.isPresent()) {
			throw new IdentityDoesNotExistsException("Player id does not exists");
		}
		
		if(!playerDetails.get().isCurrentTurn()) {
			throw new GameTurnAlreadyTakenException();
		}
		
		Random ran = new Random();
		
		int diceTurn = ran.nextInt(11);
		
		Optional<PlaceDetails> placeDetails = placeDetailsRepo.findById((diceTurn == 0 ? 11 : diceTurn));
		
		int balance = playerDetails.get().getBalance();
		GameActivity gameActivity = null;
		
		if(diceTurn == 0) {
			playerDetails.get().setBalance(balance + placeDetails.get().getPurchaseAmount());
			
			gameActivity = new GameActivity(
					new Date(), ActivityStatus.GAINED, placeDetails.get().getPurchaseAmount(), playerId, gameId);
	
				
		}else {
			
			String secondPlayer = "";
			
			if(gameDetails.get().getPlayers().get(0).getPlayerId() == playerId) {
				secondPlayer = gameDetails.get().getPlayers().get(1).getPlayerId();
				
			}else {
				secondPlayer = playerId;
			}
			
			Optional<PlayerDetails> secondPlayerDetails = playerDetailsRepo.findById(secondPlayer);
			
			boolean isPlaceExists = secondPlayerDetails.get().getBoughtPlaces()
										.stream()
										.anyMatch(places -> places.equals(placeDetails.get().getPlaceName()));
			
			if(isPlaceExists){
				
				playerDetails.get().setBalance(balance - placeDetails.get().getPurchaseAmount());
				
				gameActivity = new GameActivity(
						new Date(), ActivityStatus.PAID_RENT, placeDetails.get().getRentAmount(), playerId, gameId);
				
				if(playerDetails.get().getBalance() < 0) {
					
					gameDetails.get().setGameStatus(GameStatus.FINISHED);
					gameDetails.get().setWinner(secondPlayer);
					gameDetails.get().setEndTime(new Date());
					
					gameDetailsRepository.save(gameDetails.get());
				}
				
			}else {
				
				if (playerDetails.get().getBalance() > placeDetails.get().getPurchaseAmount()) {
					
					playerDetails.get().setBalance(balance - placeDetails.get().getPurchaseAmount());
					
					gameActivity = new GameActivity(
							new Date(), ActivityStatus.BOUGHT, placeDetails.get().getRentAmount(), playerId, gameId);
					
					playerDetails.get().getBoughtPlaces().add(placeDetails.get().getPlaceName());
				}
			}
			
		}
		
		playerDetailsRepo.save(playerDetails.get());
		gameActivityRepo.save(gameActivity);
		
		if(gameDetails.get().getGameStatus().equals(GameStatus.FINISHED)) {
			return gameDetails.get();
			
		}else {
			return gameActivity;
		}
		
	}

}
