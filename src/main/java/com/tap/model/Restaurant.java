package com.tap.model;

public class Restaurant {

	private int restaurantId;
	private String restaurantName;
	private String imagepath;
	private double rating;
	private int eta;
	private String cusineType;
	private String address;
	private boolean isActive;
	private int restaurantOwnerId;
	
	
	
	public Restaurant() {
		
	}

	public Restaurant(int restaurantId, String restaurantName, String imagepath, double rating, int eta,
			String cusineType, String address, boolean isActive, int restaurantOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.imagepath = imagepath;
		this.rating = rating;
		this.eta = eta;
		this.cusineType = cusineType;
		this.address = address;
		this.isActive = isActive;
		this.restaurantOwnerId = restaurantOwnerId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}

	public void setRestaurantOwnerId(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return restaurantId+" "+restaurantName+" "+imagepath+" "+rating+" "+eta+" "+cusineType+" "+address+" "+isActive+" "+restaurantOwnerId;
	}
	
	
}
