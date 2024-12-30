package com.tap.model;

public class Menu {
	
	private int menuId;
	private String itemName;
	private double itemPrice;
	private String description;
	private String imagePath;
	private boolean isAvailable;
	private int restaurantId;
	private double rating;
	

	
	public Menu() {
		
	}

	public Menu(int menuId, String itemName, double itemPrice, String description, String imagePath,
			boolean isAvailable, int restaurantId, double rating) {
		super();
		this.menuId = menuId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.description = description;
		this.imagePath = imagePath;
		this.isAvailable = isAvailable;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	

}
