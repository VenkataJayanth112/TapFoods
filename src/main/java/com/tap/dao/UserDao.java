package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao {
	
	void addUser(User user);
	
	User getUser(int id);
	
	void updateUser(User user);
	
	void deleteUser(int userId);
	
	List<User> getAllUsers();
	
	public boolean isValidUser(String userName, String password);
	
	void addUserSignUp(User user);
	
	User getUserByuserNameAndPassword(String userName,String password);
	
}
