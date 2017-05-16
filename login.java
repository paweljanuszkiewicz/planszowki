package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
				
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
			
		String login = request.getParameter("login");
		String haslo = request.getParameter("haslo");
		
		String message = "";
		
		out.print(login);
		out.print(haslo);
		
		// enter to a DB
		String connectionString = "jdbc:mysql://localhost:3306/planszowki?verifyServerCertificate=false&useSSL=true";
		Connection connection;
		Statement command;
		ResultSet query = null;
		int countUser = 0;
		String imie = "";
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		}
		catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		try {
			connection = DriverManager.getConnection(connectionString, "root", "password");
			command = connection.createStatement();
			
			query = command.executeQuery("SELECT COUNT(login) FROM uzytkownicy WHERE login = '" + login + "' and haslo = '" + haslo + "';");
			if (query.first()) {
				countUser = query.getInt(1);
			}
			
			if (countUser != 1)  {
				message = "Błędny login lub hasło.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				query.close();
				return;
			}
			else {
				query = command.executeQuery("SELECT imie FROM uzytkownicy WHERE login = '" + login + "' and haslo = '" + haslo + "';");
				if (query.first()) {
					imie = query.getString(1);
				}
				query.close();
				out.print(imie);
				HttpSession session = request.getSession();
				session.setAttribute("name", imie);
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
