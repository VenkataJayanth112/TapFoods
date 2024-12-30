package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDao;
import com.tap.model.Orders;

public class OrderDaoImp implements OrderDao{
	
	final static String ADD_ORDER="insert into orders( `orderId`,`restaurentId`, `userId`, `toatalAmount`, `modeOfPayment`, `orderStatus`) values(?,?,?,?,?,?)";
	final static String DELETE="DELETE from `order` where `orderId`=?";
	final static String SELECT_ONE="select * from `order` where `orderId`=?";
	final static String UPDATE="UPDATE `order` set `restaurentId`=? , `userId`=?, `toatalAmount`=?,`modeOfPayment`=?,`orderStatus`=? WHERE `orderId`=?";
	final static String SELECTALL_QUERY="select * from `order`";
	
	Connection connection;
	PreparedStatement preparedStatement;
	
	
	public OrderDaoImp(){
		
		try {
			
			Class.forName("com.tap.cj.jdbc.Driver");
			System.out.println("driver loaded");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods","root","root");
			System.out.println("connection established");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void addOrder(Orders order) {
		
		int orderId=generateUniqueOrderId();
		
		try {
			preparedStatement=connection.prepareStatement(ADD_ORDER);
			
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, order.getRestaurantId());
			preparedStatement.setInt(3, order.getUserId());
			preparedStatement.setDouble(4, order.getTotalAmount());
			preparedStatement.setString(5,order.getModeOfPayment());
			preparedStatement.setString(6, order.getStatus());
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}


	@Override
	public Orders getOrder(int orderId) {
		
		PreparedStatement prepareStatement=null;
		ResultSet res=null;
		Orders order=null;
		
		try {
			prepareStatement =connection.prepareStatement(SELECT_ONE);
			
			prepareStatement.setInt(1, orderId);
			
			res=prepareStatement.executeQuery();
			
			if(res.next()) {
				int restaurentId=res.getInt("restaurentId");
				int userId=res.getInt("userId");
				double toatalPayment=res.getDouble("toatalAmount");
				String modeOfPayment=res.getString("modeOfPayment");
				String orderStatus=res.getString("orderStatus");
				
				order=new Orders(orderId, restaurentId, userId, toatalPayment, modeOfPayment, orderStatus);
						
			}
			}catch(SQLException e) {
				e.printStackTrace();
		}
		
		return order;
	}

	@Override
	public void updateOrder(Orders order) {
		
		PreparedStatement prepareStatement=null;
		
		try {
			prepareStatement=connection.prepareStatement(UPDATE);
			
			prepareStatement.setInt(1,order.getRestaurantId() );
			prepareStatement.setInt(2, order.getUserId());
			prepareStatement.setDouble(3, order.getTotalAmount());
			prepareStatement.setString(4, order.getModeOfPayment());
			prepareStatement.setString(5,order.getStatus());
			
			prepareStatement.setInt(6, order.getUserId());
			
			prepareStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrder(int orderId) {
		
		PreparedStatement prepareStatement=null;
		
		try {
			prepareStatement = connection.prepareStatement(DELETE);
			
			prepareStatement.setInt(1, orderId);
			prepareStatement.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Orders> getAll() {
		
		Statement statement=null;
		
		ResultSet res=null;
		
		List<Orders> list=new ArrayList();
		
		try {
			statement =connection.createStatement();
			res=statement.executeQuery(SELECTALL_QUERY);
			
			while(res.next()) {
				int orderId=res.getInt("orderId");
				int restaurantId=res.getInt("restaurentId");
				int userId=res.getInt("userId");
				double totalAmount=res.getDouble("toatalAmount");
				String modeOfPayment=res.getString("modeOfPayment");
				String orderStatus=res.getString("orderStatus");
				
				Orders order=new Orders(orderId, restaurantId, userId, totalAmount, modeOfPayment, orderStatus);
				
				list.add(order);			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	

	private int generateUniqueOrderId() {
		
		int lastOrderId=1;
		
		try {
			lastOrderId=getLastOrderIdFromDatabase();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int newOrderId=lastOrderId + 1;
		
		try {
			updateLastOrderIdInDatabase(newOrderId);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return newOrderId;
	}

	

	private int getLastOrderIdFromDatabase() throws Exception{
		
		int lastOrderId=0;
		String query="SELECT max(orderId) FROM `order`";
		
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet=statement.executeQuery()) {
			if(resultSet.next()) {
				lastOrderId=resultSet.getInt(1);
			}
		}
				
		
		return lastOrderId;
	}
	
	private void updateLastOrderIdInDatabase(int newOrderId) throws SQLException{
		
		String query= "update last_order_id_table set last_order_id=?";
		
		try(PreparedStatement statement =connection.prepareStatement(query)){
			statement.setInt(1, newOrderId);
			statement.executeUpdate();
		}
		
		
	}

}
