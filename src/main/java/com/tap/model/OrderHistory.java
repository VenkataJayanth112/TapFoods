package com.tap.model;

import java.util.Date;

public class OrderHistory {
	
	private int orderHistoryId;
	private int userId;
	private int orderId;
	
	//private Date orderDate;
	//private double totalAmount;
	//private String status;
	
	
	
	
	
	public OrderHistory() {
		
	}

	public OrderHistory(int orderHistoryId, int userId, int orderId) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.userId = userId;
		this.orderId = orderId;
	}

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderHistoryId=" + orderHistoryId + ", userId=" + userId + ", orderId=" + orderId + "]";
	}
	
	
	
	

}
