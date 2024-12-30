package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImp.OrderDaoImp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Orders;
import com.tap.model.User;


@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	
	private OrderDaoImp orderDaoImp;
	
	public void init() {
		orderDaoImp=new OrderDaoImp();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		
		Cart cart=(Cart)session.getAttribute("cart");
		User user=(User)session.getAttribute("user");
		
		if(cart !=null) {
			
			double totalPrice = 0;
			for(CartItem item:cart.getItems().values()) {
				
				totalPrice += item.getPrice() * item.getQuantity();
				
			}
			
			session.setAttribute("totalPrice", totalPrice);
			
		}else {
			String paymentMethod = req.getParameter("payment");
			
			Orders order=new Orders();
			
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setUserId(user.getUserId());
			order.setModeOfPayment(paymentMethod);
			order.setStatus("pending");
			
			orderDaoImp.addOrder(order);
			
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			
			
		}
		
		resp.sendRedirect("Confirmation.jsp");
		
	}

}











