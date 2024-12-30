package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;

public class OrderItemDaoImp implements OrderItemDao{
	
	//orderItemId, orderId, menuId, userId, quantity, itemTotal, rating, price, imagePath
	
	static final String INSERT= "INSERT INTO `orderitem`( `userId`,`restaurantId`, `menuId`, `itemName`, `rating`, `quantity`, `price`,`totalAmount`,`imagePath`)VALUES(?,?,?,?,?,?,?,?,?)";
	final static String SELECT = "SELECT * FROM `orderitem` WHERE `orderItemId`=?";
	final static String SELECT_ALL = "SELECT * FROM `orderitem`";
	final static String UPDATE = "UPDATE `orderitem` set `userId`=?,`restaurantId`=?, `menuId`=?, `itemName`=?, `rating`=?, `quantity`=?, `price`=?, `totalAmount`=? WHERE `orderItemId`=?";
	final static String DELETE = "DELETE FROM `orderitem` WHERE `orderItemId`=?";
	final static String SELECT_ALL_BY_USERID = "SELECT * FROM `orderitem` Where `userId`=?";

	
	
	
	Connection connection=null;
	
	public OrderItemDaoImp() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods","root","root");
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		
		PreparedStatement prepareStatement=null;
		
		try {
				prepareStatement= connection.prepareStatement(INSERT);
				
				prepareStatement.setInt(1, orderItem.getUserId());
				prepareStatement.setInt(2, orderItem.getRestaurantId());
				prepareStatement.setInt(3, orderItem.getMenuId());
				prepareStatement.setString(4, orderItem.getItemName());
				prepareStatement.setDouble(5, orderItem.getRating());
				prepareStatement.setInt(6, orderItem.getQuantity());
				prepareStatement.setDouble(7, orderItem.getPrice());
				prepareStatement.setDouble(8, orderItem.getTotalAmount());
				prepareStatement.setString(9, orderItem.getImagePath());
			
				prepareStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		OrderItem orderItem = null;

		try {
			prepareStatement = connection.prepareStatement(SELECT);
			prepareStatement.setInt(1, orderItemId);

			res = prepareStatement.executeQuery();

			if (res.next()) {
				int userId = res.getInt("userId");
				int restaurantId = res.getInt("restaurantId");
				int menuId = res.getInt("menuId");
				String itemName = res.getString("itemName");
				double rating = res.getDouble("rating");
				int quantity = res.getInt("quantity");
				double price = res.getDouble("price");
				double totalAmount = res.getDouble("totalAmount");
				String imagePath = res.getString("imagePath");
				orderItem = new OrderItem(orderItemId, userId, restaurantId, menuId, orderItemId, itemName, rating, quantity, price, totalAmount, imagePath);
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return orderItem;
	
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(UPDATE);

			prepareStatement.setInt(1, orderItem.getUserId());
			prepareStatement.setInt(2, orderItem.getRestaurantId());
			prepareStatement.setInt(3, orderItem.getMenuId());
			prepareStatement.setString(4, orderItem.getItemName());
			prepareStatement.setDouble(5, orderItem.getRating());
			prepareStatement.setInt(6, orderItem.getQuantity());
			prepareStatement.setDouble(7, orderItem.getPrice());
			prepareStatement.setDouble(8, orderItem.getTotalAmount());

			prepareStatement.setInt(9, orderItem.getOrderItemId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	@Override
	public void deleteOrderItem(int orderItemId) {

		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(DELETE);
			prepareStatement.setInt(1, orderItemId);

			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public List<OrderItem> getOrderItemByOrder(int userId) {
		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		OrderItem orderItem = null;
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();

		try {
			prepareStatement = connection.prepareStatement(SELECT_ALL_BY_USERID);
			prepareStatement.setInt(1, userId);

			res = prepareStatement.executeQuery();

			while(res.next()) {
				int orderItemId = res.getInt("orderItemId");
				int userid = res.getInt("userId");
				int restaurantId = res.getInt("restaurantId");
				int menuId = res.getInt("menuId");
				String itemName = res.getString("itemName");
				double rating = res.getDouble("rating");
				int quantity = res.getInt("quantity");
				double price = res.getDouble("price");
				double totalAmount = res.getDouble("totalAmount");
				String imagePath = res.getString("imagePath");

				orderItem = new OrderItem(orderItemId, userid, restaurantId, menuId, orderItemId, itemName, rating, quantity, price, totalAmount, imagePath);
						
						
				list.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;
	}

	@Override
	public List<OrderItem> getAll() {
		Statement createStatement = null;
		ResultSet res = null;
		OrderItem orderItem = null;
		List<OrderItem> list = new ArrayList<OrderItem>();

		try {
			createStatement = connection.createStatement();

			res = createStatement.executeQuery(SELECT_ALL);
			while (res.next()) {
				int orderItemId = res.getInt("orderItemId");
				int userId = res.getInt("userId");
				int restaurantId = res.getInt("restaurantId");
				int menuId = res.getInt("menuId");
				String itemName = res.getString("itemName");
				double rating = res.getDouble("rating");
				int quantity = res.getInt("quantity");
				double price = res.getDouble("price");
				double totalAmount = res.getDouble("totalAmount");
				String imagePath = res.getString("imagePath");

				orderItem=new OrderItem(orderItemId, userId, restaurantId, menuId, orderItemId, itemName, rating, quantity, price, totalAmount, imagePath);
						
				list.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (createStatement != null)
				try {
					createStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	

}
