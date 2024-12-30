package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoImp implements MenuDao{
	
	
	//final static String ADD_MENU="insert into menu(`menuId`, `restaurantId`, `itemName`, `description`, `itemPrice`, `imagePath`, `isAvailable`, `rating`) values(?,?,?,?,?,?)";
	final static String ADD_MENU="insert into `menu`( `restaurantId`,`itemName`, `description`, `itemPrice`, `imagePath`, `isAvailable`, `rating`) values(?,?,?,?,?,?,?)";
	
	final static String SELECT_ONE="select * from `menu` where `menuId`=?";
	final static String UPDATE="update `menu` set `itemName`=?, `description`=?, `itemPrice`=?, `imagePath`=?, `isAvailable`=?, `rating`=? , `restaurantId`=? where `menuId`=?";
	final static String DELETE="delete from `menu`where `menuId`=?";
	final static String SELECT_REST_ID="Select * from `menu` where `restaurantId`=?";
	final static String SELECT_ALL="SELECT * from `menu`";
	
	PreparedStatement statement;
	Connection connection;
	
	public MenuDaoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods","root","root");
			System.out.println("connection established");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void addMenu(Menu menu) {
		
		try {
			statement=connection.prepareStatement(ADD_MENU);
			
			statement.setInt(1, menu.getRestaurantId());
			statement.setString(2, menu.getItemName());
			statement.setString(3, menu.getDescription());
			statement.setDouble(4, menu.getItemPrice());
			statement.setString(5, menu.getImagePath());
			statement.setBoolean(6, menu.isAvailable());
			statement.setDouble(7, menu.getRating());
			
			System.out.println(statement.executeUpdate());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
		Menu menu=null;
		
		try {
			statement=connection.prepareStatement(SELECT_ONE);
			
			statement.setInt(1, menuId);
			
			ResultSet res=statement.executeQuery();
			
			//menuId, restaurantId, itemName, description, itemPrice, imagePath, isAvailable, rating
			
			if(res.next()) {
				int restaurantId=res.getInt("restaurantId");
				String itemName=res.getString("itemName");
				String description=res.getString("description");
				double itemPrice=res.getDouble("itemPrice");
				String imagePath=res.getString("imagePath");
				boolean isAvailable=res.getBoolean("isAvailable");
				double rating=res.getDouble("rating");
				
				menu=new Menu(menuId, itemName, itemPrice, description, imagePath, isAvailable, restaurantId, rating);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		//"update `menu` set `itemName`=?, `description`=?, `itemPrice`=?, `imagePath`=?, `isAvailable`=?, `rating`=? where `menuId`=?";

		
		try {
			statement=connection.prepareStatement(UPDATE);
			
			statement.setString(1,menu.getItemName());
			statement.setString(2, menu.getDescription());
			statement.setDouble(3, menu.getItemPrice());
			statement.setString(4, menu.getImagePath());
			statement.setBoolean(5, menu.isAvailable());
			statement.setDouble(6, menu.getRating());
			statement.setInt(7, menu.getRestaurantId());
			statement.setInt(8, menu.getMenuId());
			
			System.out.println(statement.executeUpdate());

			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {
		
		
		try {
			statement=connection.prepareStatement(DELETE);
			
			statement.setInt(1, menuId);
			
			System.out.println(statement.executeUpdate());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Menu> getAllMenuByRestarent(int restaurantId) {
		
		//menuId, restaurantId, itemName, description, itemPrice, imagePath, isAvailable, rating
		Menu menu=null;
				
		List<Menu> list=new ArrayList();
		
		try {
			statement=connection.prepareStatement(SELECT_REST_ID);
			statement.setInt(1, restaurantId);
			
			ResultSet res=statement.executeQuery();
			
			while(res.next()){
				
				int menuId=res.getInt("menuId");
				//int restaurantId=res.getInt("restaurantId");
				
				String itemName=res.getString("itemName");
				String dedescription=res.getString("description");
				double itemPrice=res.getDouble("itemPrice");
				String imagePath=res.getString("imagePath");
				boolean isAvailable=res.getBoolean("isAvailable");
				double rating=res.getDouble("rating");
				
				menu=new Menu(menuId, itemName, itemPrice, dedescription, imagePath, isAvailable, restaurantId, rating);
				
				list.add(menu);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<Menu> getAll() {
		Statement stmt=null;
		
		ResultSet res=null;
		
		List<Menu> list=new ArrayList();
		
		try {
			
			stmt=connection.createStatement();
			res=stmt.executeQuery(SELECT_ALL);
			
			while(res.next()) {
				
				int menuId=res.getInt("menuId");
				String name=res.getString("itemName");
				String dedescription=res.getString("description");
				double itemPrice=res.getDouble("itemPrice");
				String imagePath=res.getString("imagePath");
				boolean isAvailable=res.getBoolean("isAvailable");
				int restaurantId=res.getInt("restaurantId");
				double rating=res.getDouble("rating");
				
				Menu menu=new Menu(menuId, name, itemPrice, dedescription, imagePath, isAvailable, restaurantId, rating);
						
						list.add(menu);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}


















