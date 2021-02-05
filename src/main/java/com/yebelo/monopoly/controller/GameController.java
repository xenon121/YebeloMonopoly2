package com.yebelo.monopoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yebelo.monopoly.exception.IdentityDoesNotExistsException;
import com.yebelo.monopoly.service.GameDetailsService;

@RestController
@RequestMapping("/api/v1/monopoly")
public class GameController {

	@Autowired
	private GameDetailsService gameDetailsService;
	
	@GetMapping
	public ResponseEntity<Object> getAllGameDetails(){
		
		try {
			return ResponseEntity.ok(gameDetailsService.getAllGameDetails());
		
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not able to fetch all game details");
		}
		
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<Object> getGameDetails(@PathVariable("gameId") Integer gameId){	
		
		try {
			return ResponseEntity.ok(gameDetailsService.getGameDetails(gameId));
			
		}catch (IdentityDoesNotExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
