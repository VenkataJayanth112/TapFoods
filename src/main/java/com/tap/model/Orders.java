package com.tap.model;

import java.util.Date;

public class Orders {
	
	private int orderId;
	private int restaurantId;
	private int userId;
	private double totalAmount;
	private String modeOfPayment;
	private String status;
	private Date orderDate;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return orderId+" "+restaurantId+" "+userId+" "+totalAmount+" "+modeOfPayment+" "+status+" "+orderDate;
	}
	
	public Orders() {
		
	}


	public Orders(int orderId, int restaurantId, int userId, double totalAmount, String modeOfPayment, String status) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.modeOfPayment = modeOfPayment;
		this.status = status;
		//this.orderDate = orderDate;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getModeOfPayment() {
		return modeOfPayment;
	}


	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
