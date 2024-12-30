package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.PasswordEncryptionDecryption;
import com.tap.model.User;


public class UserDaoImp implements UserDao{

	Connection connection=null;
	PreparedStatement statement=null;

	final static String ADD_USER="insert into `user`(`name`,`email`,`phoneNo`,`address`,`userName`,`password`,`role`) values(?,?,?,?,?,?,?)";
	final static String SELECT_ONE = "select * from `User` where `userId`=?" ;
	final static String UPDATE="update `user` set `name`=?,`email`=?,`phoneNo`=?,`address`=?,`userName`=?, `password`=?,`role`=?  where `userId`=?";
	final static String DELETE="delete from `user` where `userId`=?";
	final static String SELECT="select * from `user`";
	
	
	final static String SELECT_BY_USER_PSWD="select * from `user` where `userName`=? AND `password`=?";
	final static String USERNAME_VALID="select count(*) from `user` where userName=?";
	final static String INSERT_QUERY_BY_SIGNUP="insert into `user`(name`,`email`,`phoneNo`,`address`,`userName`,`password`,`role`) values(?,?,?,?,?,?,?)";
	
	
	public UserDaoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("class loaded");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods", "root", "root");
			System.out.println("class loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void addUser(User user) {

		try {
			statement=connection.prepareStatement(ADD_USER);
			statement.setString(1, user.getName());
			statement.setString(2,user.getEmail());
			statement.setLong(3, user.getPhoneNo());
			statement.setString(4,user.getAddress());
			statement.setString(5, user.getUserName());
			String encryptedPassword = PasswordEncryptionDecryption.encryption(user.getPassword());
			statement.setString(6, encryptedPassword);
			statement.setString(7, user.getRole());

			System.out.println(statement.executeUpdate());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	@Override
	public User getUser(int userId) {

		User u=null;
		try {
			statement=connection.prepareStatement(SELECT_ONE);
			statement.setInt(1,userId);
			ResultSet res=statement.executeQuery();

			if(res.next()) {
				int id1=res.getInt("userId");
				String name=res.getString("name");
				String email=res.getString("email");
				long num=res.getLong("phoneNo");
				String address=res.getString("address");
				String userName=res.getString("userName");
				String password=res.getString("password");
				String role=res.getString("role");
				LocalDateTime createDate=res.getTimestamp("createdDate").toLocalDateTime();
				LocalDateTime lastLogin=res.getTimestamp("lastLogin").toLocalDateTime();
				u=new User(id1, name, email, num, address, userName, password, role, createDate, lastLogin);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return u;
	}


	@Override
	public void updateUser(User user) {

		try {
			statement=connection.prepareStatement(UPDATE);

			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setLong(3, user.getPhoneNo());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getUserName());
			statement.setString(6,user.getPassword() );
			statement.setString(7, user.getRole());
			statement.setInt(8, user.getUserId());

			System.out.println(statement.executeUpdate());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void deleteUser(int userId) {


		try {
			statement=connection.prepareStatement(DELETE);

			statement.setInt(1, userId);

			System.out.println(statement.executeUpdate());
		} catch (Exception e) {

			e.printStackTrace();
		}


	}


	@Override
	public List<User> getAllUsers() {
		User user=null;
		
		List<User> list=new ArrayList();
		
		try {
			Statement stmt=connection.createStatement();
			ResultSet res=stmt.executeQuery(SELECT);
			
			while(res.next()) {
				int userId=res.getInt("userId");
				String name=res.getString("name");
				String email=res.getString("email");
				Long phoneNo=res.getLong("phoneNo");
				String address=res.getString("address");
				String userName=res.getString("userName");
				String password=res.getString("password");
				String role=res.getString("role");
				LocalDateTime createdate=res.getTimestamp("createdDate").toLocalDateTime();
				LocalDateTime loginDate=res.getTimestamp("lastLogin").toLocalDateTime();
				
				user=new User(userId, name, email, userId, address, userName, password, role, null, null);
				
				list.add(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}


	@Override
	public boolean isValidUser(String userName, String encryptedPassword) {
		
		PreparedStatement prepareStatement=null;
		
		ResultSet res=null;
		
		boolean isValid=false;
		
		try {
			prepareStatement=connection.prepareStatement(SELECT_BY_USER_PSWD);
			
			prepareStatement.setString(1, userName);
			prepareStatement.setString(2, encryptedPassword);
			
			res=prepareStatement.executeQuery();
			
			if(res.next()) {
				isValid =true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return isValid;
	}

	public boolean isUserExists(String userName) {
		
		boolean exists=false;
		PreparedStatement prepareStatement=null;
		
		ResultSet res=null;
		
		try {
			prepareStatement=connection.prepareStatement(USERNAME_VALID);
			
			prepareStatement.setString(1,userName);
			
			res=prepareStatement.executeQuery();
			
			if(res.next()) {
				int c=res.getInt(1);
				exists=c>0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return exists;
	}

	@Override
	public void addUserSignUp(User user) {
		
		try {
			PreparedStatement Statement=connection.prepareStatement(INSERT_QUERY_BY_SIGNUP);
			
			statement.setString(1, user.getName());
			statement.setString(2,user.getEmail());
			statement.setLong(3, user.getPhoneNo());
			statement.setString(4,user.getAddress());
			statement.setString(5, user.getUserName());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getRole());
			
			String encryptedPassword=encryption(user.getPassword());
			
			statement.setString(6,encryptedPassword);
			
			statement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public User getUserByuserNameAndPassword(String userName, String password) {
		
		ResultSet res=null;
		User user=null;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_BY_USER_PSWD);
			
			preparedStatement.setString(1, userName);
			
			String encryptedPassword=encryption(password);
			
			preparedStatement.setString(2, encryptedPassword);
			
			res=preparedStatement.executeQuery();
			
			if(res.next()) {
				userName=res.getString("userName");
				password =res.getString("password");
				
				user=new User(userName,encryptedPassword);
			}
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
		return user;
	}
	
	public static String encryption(String s) {
		
		String t="";
		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			
			t+=(char)(c+1);
		}
		
		return t;
	}

	
	public static String decryption(String s) {
		String t="";
		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			
			t+=(char)(c-1);
		}
		
		
		return t;
	}
}
