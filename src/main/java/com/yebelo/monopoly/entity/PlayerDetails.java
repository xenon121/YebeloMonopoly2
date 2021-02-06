package com.yebelo.monopoly.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class), })
@Table(name = "PLAYER_DETAILS")
public class PlayerDetails {

	private String playerId;
	
	private String playerName;
	
	private Integer balance;
	
	private List<String> boughtPlaces;
	
	private boolean isCurrentTurn;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PLAYER_ID")
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Column(name = "PLAYER_NAME")
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Column(name = "BALANCE")
	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Type(type = "json")
	@Column(name = "BOUGHT_PLACES", columnDefinition = "json")
	public List<String> getBoughtPlaces() {
		return boughtPlaces;
	}

	public void setBoughtPlaces(List<String> boughtPlaces) {
		this.boughtPlaces = boughtPlaces;
	}

	@Type(type = "yes_no")
	@Column(name = "IS_CURRENT_TURN")
	public boolean isCurrentTurn() {
		return isCurrentTurn;
	}

	public void setCurrentTurn(boolean isCurrentTurn) {
		this.isCurrentTurn = isCurrentTurn;
	}
	
}
