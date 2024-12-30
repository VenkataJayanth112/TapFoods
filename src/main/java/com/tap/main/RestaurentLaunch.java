package com.tap.main;

import java.util.List;
import java.util.Scanner;

import com.tap.daoImp.RestaurantDaoImp;
import com.tap.model.Restaurant;

public class RestaurentLaunch {
	
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		
		
		
		
		//addRestaurantDetails();
		//getResDetails();
		getAllResDetails();
		//updateDetails();
		//deleteDetails();

	}

	private static void deleteDetails() {
		// TODO Auto-generated method stub
		RestaurantDaoImp d=new RestaurantDaoImp();
		d.deleteRestaurant(3);
		
	}

	private static void updateDetails() {

		Restaurant res=new Restaurant();
		
		System.out.println("enter res name");
		res.setRestaurantName(scanner.next());
		System.out.println("enter imagePath");
		res.setImagepath(scanner.next());
		System.out.println("enter rating");
		res.setRating(scanner.nextDouble());
		System.out.println("enter eta");
		res.setEta(scanner.nextInt());
		System.out.println("enter cusineType");
		res.setCusineType(scanner.next());
		System.out.println("enter adress");
		res.setAddress(scanner.next());
		System.out.println("is active");
		res.setIsActive(scanner.nextBoolean());
		//System.out.println("enter ownerID");
		//res.setRestaurantOwnerId(scanner.nextInt());
		System.out.println("enter id");
		res.setRestaurantId(scanner.nextInt());
		RestaurantDaoImp d=new RestaurantDaoImp();
		d.updateRestaurant(res);
		
		
	}

	private static void getAllResDetails() {
		
		Restaurant res=new Restaurant();
		
		RestaurantDaoImp d=new RestaurantDaoImp();
		List<Restaurant> l=d.getAllRestaurant();
		for(Restaurant i:l) {
			System.out.println(i);
		}
	}

	private static void getResDetails() {
	
		Restaurant res=new Restaurant();
		
		RestaurantDaoImp d=new RestaurantDaoImp();
		
		
		System.out.println(d.getRestaurant(3));
		
		
		
		
	}

	private static void addRestaurantDetails() {

		
		
		Restaurant res=new Restaurant();
		
		System.out.println("enter res name");
		res.setRestaurantName(scanner.next());
		System.out.println("enter imagePath");
		res.setImagepath(scanner.next());
		System.out.println("enter rating");
		res.setRating(scanner.nextDouble());
		System.out.println("enter eta");
		res.setEta(scanner.nextInt());
		System.out.println("enter cusineType");
		res.setCusineType(scanner.next());
		System.out.println("enter adress");
		res.setAddress(scanner.next());
		System.out.println("is active");
		res.setIsActive(scanner.nextBoolean());
		//System.out.println("enter ownerID");
		//res.setRestaurantOwnerId(scanner.nextInt());
		
		RestaurantDaoImp d=new RestaurantDaoImp();
		d.addRestaurant(res);
		
	}

}
