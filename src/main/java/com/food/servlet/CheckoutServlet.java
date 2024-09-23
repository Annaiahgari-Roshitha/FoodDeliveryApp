package com.food.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodDelivery.dao.OrderDao;
import com.foodDelivery.daoIMP.OrderDaoImpl;
import com.foodDelivery.model.Cart;
import com.foodDelivery.model.CartItem;
import com.foodDelivery.model.Order;
import com.foodDelivery.model.User;


@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	
	private OrderDao orderDao;
	
	public void init() {
		orderDao = new OrderDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.sendRedirect("Checkout.jsp"); 
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("loggedInUser");
		
		if(cart != null && user != null && !cart.getItems().isEmpty()) {
			//Extract checlout form data
			String paymentmethod = request.getParameter("paymentMethod");
			String address = request.getParameter("address");
			
			//create and populate the order object
			Order order = new Order();
			order.setUserId(user.getUserid());
			System.out.println(session.getAttribute("resturantId"));
			order.setRestaurantId((int) session.getAttribute("resturantid"));
			order.setOrderDate(new Date());
			order.setStatus("Pending");
			
			//Add cart items to the order and calculate the total amount
			double totalAmount = 0;
			for(CartItem item : cart.getItems().values()) {
				//assuming order has a method to handle the logic of adding items order.addOrderItem(item);; (this was throwing error)
				totalAmount += item.getPrice() * item.getQuantity();
				
			}
			order.setTotalAmount(totalAmount);
			
			//save the order to the database
			orderDao.addOrder(order);
			
			//clear the cart and redirect to the order confiramation page
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			System.out.println("Checkout");
			response.sendRedirect("order_confirmation.jsp");
		} else {
			response.sendRedirect("cart.jsp");
		}
	}

}
