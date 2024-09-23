package com.food.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodDelivery.dao.MenuDao;
import com.foodDelivery.daoIMP.MenuDaoImpl;
import com.foodDelivery.model.Menu;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	private MenuDao menuDao;
	
	@Override
	public void init() {
		menuDao = new MenuDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantId = request.getParameter("RestaurantId");
		if(restaurantId != null) {
			try {
				List<Menu> menuList = menuDao.getAllMenuByRestaurant(Integer.parseInt(restaurantId));
				request.setAttribute("menuList", menuList);
			}
			catch (NumberFormatException e) {
				//Handle exception  (log and/or forward to an error page)
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
		dispatcher.forward(request, response);
		
	}

}
