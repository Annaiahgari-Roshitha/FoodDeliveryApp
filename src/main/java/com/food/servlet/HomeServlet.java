package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodDelivery.dao.RestaurantDao;
import com.foodDelivery.daoIMP.RestaurantDaoImpl;
import com.foodDelivery.model.Restaurant;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	private RestaurantDao restaurantDao;
	
	public void init() {
		//initalize the DAO
		restaurantDao = new RestaurantDaoImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//fetch list of restaurants
		List<Restaurant> restaurantList = restaurantDao.getAllRestaurants();
		
		//set the restaurant list as a request attribute
		request.setAttribute("restaurantList", restaurantList);
		
		//forward to home.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.include(request, response);
		
	}

}
