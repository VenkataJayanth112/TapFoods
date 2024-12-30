package com.jayanth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
	
	int count=2;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		PrintWriter out=resp.getWriter();
		String op=null;
		String name=null;
		resp.setContentType("text/html");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jee1", "root", "root");
			System.out.println("connection established");
			PreparedStatement preSt=con.prepareStatement("select * from `employee` where `email`= ? ");
			System.out.println("statement created");
			preSt.setString(1, email);
			System.out.println("email added");
			ResultSet res=preSt.executeQuery();
			while(res.next()) {
				if(res.getString("email").equals(email)) {
					op=res.getString("password");	
					System.out.println(op);
					name=res.getString("name");
					System.out.println(name);
				}
			}

			
		} catch (Exception e) {

			e.printStackTrace();
		}


		if(password.equals(op)) {
			out.print("hii "+name+" welcome to webpage" );
		}
		else if(count>0) {
			out.print("invalid email/password!"+count+" try again");
			count--;
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, resp);
		}
		else {
			out.print("your attempts over! contact admin");
		}

	}

}
