package com.yebelo.monopoly.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.yebelo.monopoly.constants.GameStatus;
import com.yebelo.monopoly.entity.GameDetails;
import java.util.List;

public interface GameDetailsRepository extends CrudRepository<GameDetails, Integer> {

	Optional<GameDetails> findByPlayersPlayerId(String playerId);
	
	long countByGameStatus(GameStatus gameStatus);
	
	List<GameDetails> findByGameStatus(GameStatus gamestatus);
}
