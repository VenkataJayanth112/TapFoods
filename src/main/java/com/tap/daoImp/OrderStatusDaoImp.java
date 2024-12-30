package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.model.OrderStatus;

public class OrderStatusDaoImp {
	
	final static String SELECT="select status from `orders` where `orderId`=?";
	
	Connection connection=null;
	
	public OrderStatusDaoImp() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods","root","root");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String fetchOrderStatus(String orderId) {
		String status=null;
		
		try {
			
			PreparedStatement statement=connection.prepareStatement(SELECT);
			
			statement.setString(1,orderId);
			
			ResultSet res=statement.executeQuery();
			
			if(res.next()) {
				status=res.getString("status");
				
				OrderStatus orderStatus=new OrderStatus(orderId,status);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
