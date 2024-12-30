package com.tap.dao;

import java.util.List;

import com.tap.model.OrderHistory;

public interface OrderHistoryDao {

	
	void addOrderHistory(OrderHistory orderHistory);
	
	OrderHistory getOrderHistory(int oderHistoryId);
	
	void updateOrderHistory(OrderHistory orderHistory);
	
	void deleteOrderHistory(int orderHistoryId);
	
	//List<OrderHistory> getOrderHistoryByUser(int userId);
	
	List<OrderHistory> getAll();
}
