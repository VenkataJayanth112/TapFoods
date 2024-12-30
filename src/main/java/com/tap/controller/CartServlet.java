package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImp.MenuDaoImp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

@WebServlet("/CallingCartServlet")
public class CartServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		Cart cart=(Cart)session.getAttribute("cart");
		
		if(cart==null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action=request.getParameter("action");
		
		if(action != null) {
			if(action.equals("add")) {
				addItemtoCart(request,cart);
			}else if(action.equals("update")) {
				updateCartItem(request,cart);
			}else if(action.equals("remove")) {
				removeItemFromCart(request,cart);
			}
		}
		
		session.setAttribute("cart",cart);
		response.sendRedirect("Cart.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
		
	}
	
	private void addItemtoCart(HttpServletRequest request, Cart cart) {
		
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		MenuDaoImp menuDaoImp=new MenuDaoImp();
		Menu menuItem=menuDaoImp.getMenu(itemId);
		
		HttpSession session=request.getSession();
		session.setAttribute("restaurantId", menuItem.getRestaurantId());
		
		
		if(menuItem != null) {
			CartItem item=new CartItem(menuItem.getMenuId(),menuItem.getRestaurantId(),menuItem.getItemName(),quantity,menuItem.getItemPrice());
			
			cart.addItem(item);
			
		}
		
	}
	
	private void updateCartItem(HttpServletRequest request, Cart cart) {
		
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		cart.updateItem(itemId, quantity);
		
		
		
	}
	

	private void removeItemFromCart(HttpServletRequest request, Cart cart) {
		
		
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		cart.removeItem(itemId);
		
	}

	
	
}


















