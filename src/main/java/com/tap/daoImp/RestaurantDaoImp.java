package com.tap.daoImp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoImp  implements RestaurantDao{
	
	Connection connection;
	
	PreparedStatement statement;
	
	
	static final String ADD_RESTAURANT="insert into restaurant(`restaurantName`,`imagePath`,`rating`,`eta`,`cusineType`,"
			+ "`restaurantAddress`,`isActive`,`restaurantOwnerId`) values(?,?,?,?,?,?,?,?)";
	//static final String ADD_RESTAURANT="insert into restaurant(`restaurantName`,`imagePath`,`rating`,`eta`,`cusineType`,`restaurantAddress`,`isActive`,`restaurantOwnerId`) values(?,?,?,?,?,?,?,?)";
	
	
	static final String SELECT_ONE="select * from `restaurant` where `restaurantId` = ?";
	
	static final String UPDATE="update `restaurant`  set `restaurantName`=?, `imagePath`=?, `rating`=?, `eta`=?, `cusineType`=?,"
			+ " `restaurantAddress`=?, `isActive`=? ,`restaurantOwnerId`=?  where `restaurantId`=?";
	
	//static final String UPDATE="update `restaurant`  set `restaurantName`=?, `imagePath`=?, `rating`=?, `eta`=?, `cusineType`=?,"
		//	+ " `restaurantAddress`=?, `isActive`=?, `restaurantOwnerId`=?  where `restaurantId`=?";
	
	static final String DELETE="delete from `restaurant` where `restaurantId` = ?";
	
	static final String SELECT="select *from `restaurant`";
	
	
	
	public RestaurantDaoImp() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver laoded");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapFoods","root","root");
			System.out.println("connection established");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant res) {

		try {
				statement=connection.prepareStatement(ADD_RESTAURANT);
				
				statement.setString(1,res.getRestaurantName());
				statement.setString(2, res.getImagepath());
				statement.setDouble(3, res.getRating());
				statement.setInt(4,res.getEta());
				statement.setString(5, res.getCusineType());
				statement.setString(6, res.getAddress());
				statement.setBoolean(7, res.getIsActive());
				statement.setInt(8,res.getRestaurantOwnerId());
				
				System.out.println(statement.executeUpdate());
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant r=null;

		try {
			statement=connection.prepareStatement(SELECT_ONE);
			
			statement.setInt(1, restaurantId);
			
			ResultSet res=statement.executeQuery();
			
			if(res.next()) {
				
				
				String name=res.getString("restaurantName");
				String imagePath=res.getString("imagePath");
				double rating=res.getDouble("rating");
				int eta=res.getInt("eta");
				String cusinetype=res.getString("cusineType");
				String address=res.getString("restaurantAddress");
				boolean isActive=res.getBoolean("isActive");
				int restaurantOwnerId=res.getInt("restaurantOwnerId");
				
				
				r=new Restaurant(restaurantId, name, imagePath, rating, eta, cusinetype, address, isActive, restaurantOwnerId);
						
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public void updateRestaurant(Restaurant res) {
		
		try {
			statement=connection.prepareStatement(UPDATE);
			
			statement.setString(1,res.getRestaurantName());
			statement.setString(2, res.getImagepath());
			statement.setDouble(3, res.getRating());
			statement.setInt(4,res.getEta());
			statement.setString(5,res.getCusineType());
			statement.setString(6, res.getAddress());
			statement.setBoolean(7, res.getIsActive());
			statement.setInt(8, res.getRestaurantOwnerId());
			statement.setInt(9, res.getRestaurantId());

			
			System.out.println(statement.executeUpdate());
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {


		try {
			statement=connection.prepareStatement(DELETE);
			
			statement.setInt(1, restaurantId);
			
			System.out.println(statement.executeUpdate());			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		
		Restaurant rest=null;
		
		List<Restaurant> list=new ArrayList();
		
		try {
			Statement stmt=connection.createStatement();
			ResultSet res=stmt.executeQuery(SELECT);
			
			while(res.next()) {
				
				int id=res.getInt("restaurantId");
				String name=res.getString("restaurantName");
				String imagePath=res.getString("imagePath");
				double rating=res.getDouble("rating");
				int eta=res.getInt("eta");
				String cusinetype=res.getString("cusineType");
				String address=res.getString("restaurantAddress");
				boolean isActive=res.getBoolean("isActive");
				int restaurantOwnerId=res.getInt("restaurantOwnerId");
				
				rest=new Restaurant(id, name, imagePath, rating, eta, cusinetype, address, isActive, restaurantOwnerId);
				
				list.add(rest);
				
			}
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return list;
	}
	
	
	
		
	
}
