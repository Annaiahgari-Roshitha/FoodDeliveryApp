package com.foodDelivery.daoIMP;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.OrderDao;
import com.foodDelivery.model.Order;

public class OrderDaoImpl implements OrderDao {
	
	private static Connection connection = null;
	private static PreparedStatement preparestatement = null;
	private static Statement statement = null;
	private static ResultSet res = null;
	
	private final static String INSERT_QUERY = "insert into `ordertable`(UserID,RestaurantID,OrderDate,TotalAmount,Status,PaymentMethod) values (?,?,?,?,?,?)";
	private final static String SELECT_QUERY ="select * from `ordertable` where OrderID=?";
	private final static String UPDATE_QUERY = "update `ordertable` set UserID=?, RestaurantID=?, OrderDate=?, TotalAmount=?, Status=?, PaymentMethod=? where OrderID=?";
	private final static String DELETE_QUERY = "delete from `ordertable` where OrderID=?";
	private final static String SELECT_BY_USER_QUERY = "select * from `ordertable` UserID=?";
	
	public OrderDaoImpl() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "Roshitha@1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void addOrder(Order Order) {
		
		try {
			preparestatement = connection.prepareStatement(INSERT_QUERY);
			preparestatement.setInt(1, Order.getUserId());
			preparestatement.setInt(2, Order.getRestaurantId());
			preparestatement.setDate(3, new java.sql.Date(Order.getOrderDate().getTime()));
			preparestatement.setDouble(4, Order.getTotalAmount());
			preparestatement.setString(5, Order.getStatus());
			preparestatement.setString(6, Order.getPaymentMethod());
			
			preparestatement.executeUpdate();
			System.out.println("insertion completed");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getOrder(int orderId) {
		
		try {
			preparestatement = connection.prepareStatement(SELECT_QUERY);
			preparestatement.setInt(1, orderId);
			res = preparestatement.executeQuery();
			
			if(res.next()) {
				int orderid = res.getInt("OrderID");
				int userid = res.getInt("UserID");
				int restaurantid = res.getInt("RestaurantID");
				Date orderdate = res.getDate("OrderDate");
				Double totalamount = res.getDouble("TotalAmount");
				String status = res.getString("Status");
				String paymentmethod = res.getString("PaymentMethod");
				
				return new Order(orderid,userid,restaurantid,orderdate,totalamount,status,paymentmethod);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrder(Order Order) {
		
		try {
			preparestatement = connection.prepareStatement(UPDATE_QUERY);
			preparestatement.setInt(1, Order.getUserId());
			preparestatement.setInt(2, Order.getRestaurantId());
			preparestatement.setDate(3, new java.sql.Date(Order.getOrderDate().getTime()));
			preparestatement.setDouble(4, Order.getTotalAmount());
			preparestatement.setString(5, Order.getStatus());
			preparestatement.setString(6, Order.getPaymentMethod());
			preparestatement.setInt(7, Order.getOrderId());
			
			preparestatement.executeUpdate();
			System.out.println("Order updated succesfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrder(int orderId) {
		
		try {
			preparestatement = connection.prepareStatement(DELETE_QUERY);
			preparestatement.setInt(1, orderId);
			preparestatement.executeUpdate();
			System.out.println("Order deleted successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getAllOrdersByUser(int userId) {
		List<Order> orderList = new ArrayList<>();
		
		try {
			preparestatement = connection.prepareStatement(SELECT_BY_USER_QUERY);
			preparestatement.setInt(1, userId);
			res = preparestatement.executeQuery();
			
			while(res.next()) {
				int orderid = res.getInt("OrderID");
				int userid = res.getInt("UserID");
				int restaurantid = res.getInt("RestaurantID");
				Date orderdate = res.getDate("OrderDate");
				Double totalamount = res.getDouble("TotalAmount");
				String status = res.getString("Status");
				String paymentmethod = res.getString("PaymentMethod");
				
				Order e = new Order(orderid,userid,restaurantid,orderdate,totalamount,status,paymentmethod);
				orderList.add(e);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



}
