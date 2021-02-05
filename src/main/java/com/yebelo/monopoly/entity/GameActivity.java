package com.yebelo.monopoly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.yebelo.monopoly.constants.ActivityStatus;

@Entity
@Table(name = "GAME_ACTIVITIES")
public class GameActivity {

	private String activityId;
	
	private Date activityTime;
	
	private ActivityStatus activityStatus;
	
	private Integer amount;
	
	private String playerId;
	
	private Integer gameId;

	public GameActivity() {
		
	}
	
	public GameActivity(Date activityTime, ActivityStatus activityStatus, Integer amount,
			String playerId, Integer gameId) {
		
		super();
		this.activityTime = activityTime;
		this.activityStatus = activityStatus;
		this.amount = amount;
		this.playerId = playerId;
		this.gameId = gameId;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ACTIVITY_ID")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_TIME")
	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ACTIVITY_STATUS")
	public ActivityStatus getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(ActivityStatus activityStatus) {
		this.activityStatus = activityStatus;
	}

	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "PLAYER_ID")
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Column(name = "GAME_ID")
	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	
}
