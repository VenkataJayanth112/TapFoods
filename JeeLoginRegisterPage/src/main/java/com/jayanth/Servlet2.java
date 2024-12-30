package com.jayanth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("username");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("class loaded");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jee1","root","root");
			PreparedStatement stmt=con.prepareStatement("insert into employee(name,email,password,number,address) values(?,?,?,?,?)");
		
			stmt.setString(1,name);
			stmt.setString(2, req.getParameter("email"));
			stmt.setString(3, req.getParameter("password"));
			stmt.setString(4, req.getParameter("number"));
			stmt.setString(5, req.getParameter("address"));
			stmt.executeUpdate();
			
			PrintWriter out=resp.getWriter();
			out.print("hii "+name+" welcome to home page");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
