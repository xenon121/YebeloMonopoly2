package com.yebelo.monopoly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLACE_DETAILS")
public class PlaceDetails {

	private Integer placeId;
	
	private String placeName;
	
	private Integer purchaseAmount;
	
	private Integer rentAmount;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLACE_ID")
	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	@Column(name = "PLACE_NAME")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Column(name = "PURCHASE_AMOUNT")
	public Integer getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Integer purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	@Column(name = "RENT_AMOUNT")
	public Integer getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(Integer rentAmount) {
		this.rentAmount = rentAmount;
	}
	
}
