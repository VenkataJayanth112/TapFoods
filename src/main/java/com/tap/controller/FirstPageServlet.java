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


@WebServlet("/Home")
public class FirstPageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDaoImp resDao=new RestaurantDaoImp();
		List<Restaurant> list=resDao.getAllRestaurant();
		
		HttpSession session=req.getSession();
		session.setAttribute("restaurantList", list);
		
		RequestDispatcher rd=req.getRequestDispatcher("Restaurant.jsp");
		rd.forward(req, resp);
		
	}

}
