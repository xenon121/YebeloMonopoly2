package com.yebelo.monopoly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yebelo.monopoly.exception.GameSessionAlreadyExists;
import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.service.GameDetailsService;

@RestController
@RequestMapping("/api/v1/monopoly/game-info")
public class GameController {

	@Autowired
	private GameDetailsService gameDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@GetMapping
	public ResponseEntity<Object> getAllGameDetails(){
		
		try {
			return ResponseEntity.ok(gameDetailsService.getAllGameDetails());
		
		}catch (Exception e) {
			logger.error("not able to fetch all game details...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not able to fetch all game details");
		}
		
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<Object> getGameDetails(@PathVariable("gameId") Integer gameId){	
		
		try {
			return ResponseEntity.ok(gameDetailsService.getGameDetails(gameId));
			
		}catch (IdentityDoesNotExistsException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/player/{playerId}")
	public ResponseEntity<Object> getGameDetailsByPlayerId(@PathVariable("playerId") String playerId){	
		
		try {
			return ResponseEntity.ok(gameDetailsService.getGameDetailsByPlayerId(playerId));
			
		}catch (IdentityDoesNotExistsException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> createNewGame(){
		
		try {
			return ResponseEntity.ok(gameDetailsService.createNewGame());
		
		}catch (GameSessionAlreadyExists e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch (Exception e) {
			logger.error("not able to create new game...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not able to create new game...");
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> finishGame(){
		
		try {
			return ResponseEntity.ok(gameDetailsService.finishGame());
		
		}catch (Exception e) {
			logger.error("unable to perform finish game...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to perform finish game...");
		}
	}
	
}
