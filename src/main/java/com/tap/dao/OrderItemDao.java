package com.tap.dao;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDao {
	
	void addOrderItem(OrderItem orderItem);
	
	OrderItem getOrderItem(int orderItemId);
	
	void updateOrderItem(OrderItem orderItem);
	
	void deleteOrderItem(int orderItemId);
	
	List<OrderItem> getOrderItemByOrder(int userId);
	
	List<OrderItem> getAll();

}
