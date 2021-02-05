package com.yebelo.monopoly.repo;

import org.springframework.data.repository.CrudRepository;

import com.yebelo.monopoly.entity.PlayerDetails;

public interface PlayerDetailsRepo extends CrudRepository<PlayerDetails, String> {

	
}
