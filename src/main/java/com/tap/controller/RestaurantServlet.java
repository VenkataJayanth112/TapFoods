package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImp.RestaurantDaoImp;
import com.tap.model.Restaurant;

@WebServlet("/callingServlet")
public class RestaurantServlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDaoImp rest = new RestaurantDaoImp();
		
		List<Restaurant> restaurants=rest.getAllRestaurant();
		
		HttpSession ses=req.getSession();
		
		ses.setAttribute("restaurants", restaurants);
		
		RequestDispatcher rd=req.getRequestDispatcher("restaurant.jsp");
		
		rd.forward(req, resp);
		
	}
}
