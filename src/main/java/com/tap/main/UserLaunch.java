package com.tap.main;

import java.util.List;
import java.util.Scanner;

import com.tap.dao.UserDao;
import com.tap.daoImp.UserDaoImp;
import com.tap.model.User;

public class UserLaunch {
	
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {

		//addUserDetails();
		//getUserDetails();
		//getAllUserDetails();
		//updateDetails();
		//deleteDetails();
		
		
	}
	
	
	
	private static void deleteDetails() {
		
		UserDao u = new UserDaoImp();
		System.out.println("enter id");
		u.deleteUser(scanner.nextInt());
		
	}



	private static void updateDetails() {
		
		User user = new User();
		System.out.println("enter id");
		user.setUserId(scanner.nextInt());
		System.out.println("enter name");
		user.setName(scanner.next());
		System.out.println("enter email");
		user.setEmail(scanner.next());
		System.out.println("enter phoneNo");
		user.setPhoneNo(scanner.nextLong());
		System.out.println("enter address");
		user.setAddress(scanner.next());
		System.out.println("enter userName");
		user.setUserName(scanner.next());
		System.out.println("enter password");
		user.setPassword(scanner.next());
		System.out.println("enter role");
		user.setRole(scanner.next());
		
		UserDao u = new UserDaoImp();
		u.updateUser(user);
		
	}



	private static void getAllUserDetails() {

		UserDao user = new UserDaoImp();
		
		List<User> l=user.getAllUsers();
		
		for(User u:l) {
			System.out.println(u);
		}
	}

	private static void getUserDetails() {
		
		System.out.println("enter userId");
		
		
		UserDao u=new UserDaoImp();
		
		User user=u.getUser(scanner.nextInt());
		
		if(user!=null) {
			System.out.printf("%d %s %s %d %s %s %s %s %s %s", user.getUserId(),user.getName(),user.getEmail(),user.getPhoneNo(),
					user.getAddress(),user.getUserName(),user.getPassword(),user.getRole(),user.getCreatedDate(),user.getLastLogin());
		}
		else {
			System.out.println("data not found");
		}
	}

	public static void addUserDetails() {
		
		
		User user=new User();
		
		System.out.println("enter name");
		user.setName(scanner.next());
		System.out.println("enter email");
		user.setEmail(scanner.next());
		System.out.println("enter phoneNo");
		user.setPhoneNo(scanner.nextLong());
		System.out.println("entwer address");
		user.setAddress(scanner.next());
		System.out.println("enter userName");
		user.setUserName(scanner.next());
		System.out.println("entwer password");
		user.setPassword(scanner.next());
		System.out.println("enter role");
		user.setRole(scanner.next());
		
		UserDao d = new UserDaoImp();
		d.addUser(user);
	}
	
	

}
