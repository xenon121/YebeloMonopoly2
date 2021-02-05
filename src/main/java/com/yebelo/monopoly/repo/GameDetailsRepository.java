package com.yebelo.monopoly.repo;

import org.springframework.data.repository.CrudRepository;

import com.yebelo.monopoly.entity.GameDetails;

public interface GameDetailsRepository extends CrudRepository<GameDetails, Integer> {

}
