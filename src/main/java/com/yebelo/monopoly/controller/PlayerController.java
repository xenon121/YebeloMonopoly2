package com.yebelo.monopoly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yebelo.monopoly.exception.GameTurnAlreadyTakenException;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.exception.MaximumPlayerCreatedException;
import com.yebelo.monopoly.exception.NotEnoughPlayerException;
import com.yebelo.monopoly.service.PlayerDetailsService;

@RestController
@RequestMapping("/api/v1/monopoly/player")
public class PlayerController {

	@Autowired
	private PlayerDetailsService playerDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@PostMapping("/game/{gameId}")
	public ResponseEntity<Object> createNewPlayer(
			@PathVariable("gameId") Integer gameId, 
			@RequestParam("playerName") String playerName){
		
		try {
			return ResponseEntity.ok(playerDetailsService.createNewPlayer(gameId, playerName));
		
		}catch (MaximumPlayerCreatedException | IdentityDoesNotExistsException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch (Exception e) {
			logger.error("not able to create new player...");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not able to create new player...");
		}
	}
	
	@PostMapping("/{playerId}/game/{gameId}")
	public ResponseEntity<Object> playMonopoly(
			@PathVariable("playerId") String playerId, 
			@PathVariable("gameId") Integer gameId){
		
		try {
			return ResponseEntity.ok(playerDetailsService.playMonopoly(playerId, gameId));
		
		}catch (GameTurnAlreadyTakenException | IdentityDoesNotExistsException | NotEnoughPlayerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch (Exception e) {
			logger.error("not able to take turn for play...");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not able to take turn for play...");
		}
	}
	
	
}
