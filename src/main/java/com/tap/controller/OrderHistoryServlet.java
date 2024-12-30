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

import com.tap.daoImp.OrderItemDaoImp;
import com.tap.model.OrderItem;
import com.tap.model.User;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user=(User) session.getAttribute("user");
		
		System.out.println(user);
		
		int userId=user.getUserId();
		
		OrderItemDaoImp orderItemDaoImp= new OrderItemDaoImp();
		
		List<OrderItem> allOrderItems=orderItemDaoImp.getOrderItemByOrder(userId);
		
		session.setAttribute("allOrderItems", allOrderItems);
		
		RequestDispatcher rd=req.getRequestDispatcher("OrderHistory.jsp");
		rd.forward(req, resp);
		
	}

}
