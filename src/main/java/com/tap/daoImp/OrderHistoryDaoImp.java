package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderHistoryDao;
import com.tap.model.OrderHistory;

public class OrderHistoryDaoImp implements OrderHistoryDao{
	
	//orderHistoryId, order, userId, orderId, orderDate, totalAmount, status
	
	final static String INSERT_QUERY="Insert into `orderHistory`(`orderHistoryId`,`userId` `orderId`) values (?,?,?)";
	final static String UPDATE_QUERY ="update `orderhistory` set `userId`=?,`orderId`=?, where `orderHistoryId`=?";
	final static String SELECT_QUERY="select * from `orderhistory` where `orderHistoryId`=?";
	final static String DELETE_QUERY="delete from `orderhistory` where `orderHistoryId`=?";
	final static String SELECT_ALL="select * from `orderhistory`";
	
	Connection connection=null;
	
	PreparedStatement preparedStatement=null;
	
	ResultSet res=null;
	
	public OrderHistoryDaoImp() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods","root","root");
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		
		
		
		try {
			preparedStatement=connection.prepareStatement(INSERT_QUERY);
			
			preparedStatement.setInt(1, orderHistory.getOrderHistoryId());
			preparedStatement.setInt(2, orderHistory.getUserId());
			preparedStatement.setInt(3,orderHistory.getOrderId());
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		
		OrderHistory orderHistory=null;
		
		try {
			preparedStatement=connection.prepareStatement(SELECT_QUERY);
			
			preparedStatement.setInt(1, orderHistoryId);
			
			
			res=preparedStatement.executeQuery();
			
			if(res.next()) {
				
				int userId=res.getInt("userId");
				int orderId=res.getInt("orderid");
				
				orderHistory= new OrderHistory(orderHistoryId, userId, orderId);		
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderHistory;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		
		try {
			
				preparedStatement=connection.prepareStatement(UPDATE_QUERY);
				
				preparedStatement.setInt(1, orderHistory.getUserId());
				preparedStatement.setInt(2, orderHistory.getOrderId());
				
				preparedStatement.setInt(3, orderHistory.getOrderHistoryId());
				
				preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		
		try {
			preparedStatement=connection.prepareStatement(DELETE_QUERY);
			
			preparedStatement.setInt(1, orderHistoryId);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<OrderHistory> getAll() {
		
		Statement statement=null;
		
		ResultSet res=null;
		
		List<OrderHistory> list=new ArrayList<>();
		
		try {
			statement=connection.createStatement();
			res=statement.executeQuery(SELECT_ALL);
			
			while(res.next()) {
				
				int orderHistoryId = res.getInt("orderHistoryId");
				int userId = res.getInt("userId");
				int orderId = res.getInt("orderId");
				
				OrderHistory orderHistory=new OrderHistory(orderHistoryId, userId, orderId);
				
				list.add(orderHistory);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

}





















