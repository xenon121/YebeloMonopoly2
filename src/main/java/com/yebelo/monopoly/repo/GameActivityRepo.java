package com.yebelo.monopoly.repo;

import org.springframework.data.repository.CrudRepository;

import com.yebelo.monopoly.entity.GameActivity;

public interface GameActivityRepo extends CrudRepository<GameActivity, String> {

}
