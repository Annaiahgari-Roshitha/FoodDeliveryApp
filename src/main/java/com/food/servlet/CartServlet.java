package com.food.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodDelivery.dao.MenuDao;
import com.foodDelivery.daoIMP.MenuDaoImpl;
import com.foodDelivery.model.Cart;
import com.foodDelivery.model.CartItem;
import com.foodDelivery.model.Menu;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(request, cart);
		}
		else if (action.equals("update")) {
			upadteCartItem(request, cart);
		}
		else if (action.equals("remove")) {
			removeItemFromCart(request, cart);
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("cart.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
	
	private void addItemToCart(HttpServletRequest request, Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		MenuDao menuDao = new MenuDaoImpl();
		Menu menuItem = menuDao.getMenu(itemId);
		
		HttpSession session = request.getSession();
		session.setAttribute("resturantId", menuItem.getRestaurantId());
		
		if(menuItem != null) {
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice()
					);
					cart.addItem(item);
		}
	}
	
	
	private void upadteCartItem(HttpServletRequest request, Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
	}
	
	private void removeItemFromCart(HttpServletRequest request, Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		cart.removeItem(itemId);
	}

}
