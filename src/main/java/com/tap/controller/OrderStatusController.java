package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.OrderStatusDaoImp;


@WebServlet("/trackcontroller")
public class OrderStatusController extends HttpServlet {
	
	public void getOrderStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		String orderId=request.getParameter("orderId");
		
		OrderStatusDaoImp orderStatusDaoImp=new OrderStatusDaoImp();
		String status =orderStatusDaoImp.fetchOrderStatus(orderId);
		
		request.setAttribute("status", status);
		
		RequestDispatcher rd=request.getRequestDispatcher("trackOrderStatus.jsp");
		
		rd.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException {
		
		OrderStatusController orderStatusController =new OrderStatusController();	
		
		orderStatusController.getOrderStatus(request,response);
		
	}
	
}