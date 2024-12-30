package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImp.UserDaoImp;
import com.tap.model.PasswordEncryptionDecryption;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private UserDaoImp userDaoImp;
	
	public void init() throws ServletException {
		userDaoImp=new UserDaoImp();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName=req.getParameter("username");
		String password=req.getParameter("password");
		
		String encryptedPassword= PasswordEncryptionDecryption.encryption(password);
		
		boolean isValid =userDaoImp.isValidUser(userName, encryptedPassword);
		
		HttpSession session=req.getSession();
		
		Integer attempts=(Integer)session.getAttribute("attempts");
		
		if(attempts == null) {
			attempts = 0 ;
		}
		
		if(isValid) {
			
			session.setAttribute("userName", userName);
			resp.sendRedirect("RestaurantMain.jsp");
			
		} else {
			attempts++;
			
			session.setAttribute("attempts", attempts);
			if(attempts >= 3) {
				resp.sendRedirect("Login.jsp?error=max_attempts");
				
			}else {
				resp.sendRedirect("Login.jsp?error=invalid_credentials");
			}
		}
	}
}
















