package com.yebelo.monopoly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yebelo.monopoly.constants.GameStatus;

@Entity
@Table(name = "GAME_DETAILS")
public class GameDetails {

	private Integer gameId;
	
	private Date startTime;
	
	private Date endTime;
	
	private String winner;
	
	private GameStatus gameStatus;
	
	private List<PlayerDetails> players;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAME_ID")
	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "WINNER")
	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "GAME_STATUS")
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "GAME_DETAIL")
	public List<PlayerDetails> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDetails> players) {
		this.players = players;
	}
	
}
