package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.MenuDaoImp;
import com.tap.model.Menu;


@WebServlet("/CallingMenuServlet")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDaoImp m=new MenuDaoImp();
		
		List<Menu> menuList=m.getAllMenuByRestarent(restaurantId);
		
		req.setAttribute("Menus", menuList);
		
		RequestDispatcher rd=req.getRequestDispatcher("Menu.jsp");
		rd.forward(req, resp);
	}

}
