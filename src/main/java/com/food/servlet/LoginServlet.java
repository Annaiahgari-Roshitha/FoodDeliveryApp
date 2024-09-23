package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodDelivery.dao.UserDaoo;
import com.foodDelivery.daoIMP.UserDaoImpl;
import com.foodDelivery.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UserDaoo userDao;
	
	public void init() {
		userDao = new UserDaoImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login attempt recieved");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userDao.getUserByUsername(username);
		
		if (user != null && user.getPassword().equals(password)) {
			//create a session and redirect to home page
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", user);
			response.sendRedirect("home");
			
		}
		else {
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
