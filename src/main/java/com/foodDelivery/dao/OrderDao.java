package com.foodDelivery.dao;

import java.util.List;

import com.foodDelivery.model.Order;

public interface OrderDao {
	void addOrder(Order Order);
	Order getOrder(int orderId);
	void updateOrder(Order Order);
	void deleteOrder(int orderId);
	List<Order> getAllOrdersByUser(int userId);
	
}
