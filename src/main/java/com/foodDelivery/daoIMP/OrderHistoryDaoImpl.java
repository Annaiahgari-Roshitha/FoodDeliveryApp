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

import com.foodDelivery.dao.OrderHistoryDao;
import com.foodDelivery.model.OrderHistory;

public class OrderHistoryDaoImpl implements OrderHistoryDao {
	
	private static Connection connection = null;
	private static PreparedStatement preparestatment = null;
	private static Statement statement = null;
	private static ResultSet res = null;
	
	private final static String INSERT_QUERY = "insert into `orderhistory` (UserID, OrderID, OrderDate, TotalAmount, Status) values (?,?,?,?,?)";
	private final static String SELECT_QUERY = "select * from `orderhistory` where OrderHistoryID=?";
	private final static String UPDATE_QUERY = "update `orderhistory` set UserID=?, OrderID=?, OrderDate=?, TotalAmount=?, Status=? where orderHistoryID=?";
	private final static String DELETE_QUERY = "DELETE FROM `orderhistory` where OrderHistoryID=?";
	private final static String SELECT_BY_USER_QUERY = "select * from `orderhistory` where UserID=?";
	
	
	public OrderHistoryDaoImpl() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "Roshitha@1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		
		try {
			
			preparestatment = connection.prepareStatement(INSERT_QUERY);
			preparestatment.setInt(1, orderHistory.getUserId());
			preparestatment.setInt(2, orderHistory.getOrderId());
			preparestatment.setDate(3, new java.sql.Date(orderHistory.getOrderDate().getTime()));
			preparestatment.setDouble(4,  orderHistory.getTotalAmount());
			preparestatment.setString(5, orderHistory.getStatus());
			
			int i = preparestatment.executeUpdate();
			System.out.println("insertion completed");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		
		try {
			preparestatment = connection.prepareStatement(SELECT_QUERY);
			preparestatment.setInt(1, orderHistoryId);
			res = preparestatment.executeQuery();
			
			if(res.next()) {
				int orderhistoryid = res.getInt("OrderHistoryID");
				int userid = res.getInt("UserID");
				int orderid = res.getInt("OrderID");
				Date orderdate = res.getDate("OrderDate");
				Double totalamount = res.getDouble("TotalAmount");
				String status = res.getString("Status");;
				
				return new OrderHistory(orderhistoryid,userid,orderid,orderdate,totalamount,status);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		
		try {
			preparestatment = connection.prepareStatement(UPDATE_QUERY);
			preparestatment.setInt(1, orderHistory.getUserId());
			preparestatment.setInt(2, orderHistory.getOrderId());
			preparestatment.setDate(3, new java.sql.Date(orderHistory.getOrderDate().getTime()));
			preparestatment.setDouble(4,  orderHistory.getTotalAmount());
			preparestatment.setString(5, orderHistory.getStatus());
			preparestatment.setInt(6, orderHistory.getOrderHistoryId());
			
			preparestatment.executeUpdate();
			System.out.println("Order history update succcessfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		
		try {
			preparestatment = connection.prepareStatement(DELETE_QUERY);
			preparestatment.setInt(1, orderHistoryId);
			preparestatment.executeUpdate();
			System.out.println("Order history deleted successfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderHistory> getOrderHistoryByUser(int userId) {
		
		List<OrderHistory> orderHistoryList = new ArrayList<>();
		
		try {
			preparestatment = connection.prepareStatement(SELECT_BY_USER_QUERY);
			preparestatment.setInt(1, userId);
			res = preparestatment.executeQuery();
			
			while(res.next()) {
				int orderhistoryid = res.getInt("OrderHistoryID");
				int userid = res.getInt("UserID");
				int orderid = res.getInt("OrderID");
				Date orderdate = res.getDate("OrderDate");
				Double totalamount = res.getDouble("TotalAmount");
				String status = res.getString("Status");;
				
				OrderHistory e = new OrderHistory(orderhistoryid,userid,orderid,orderdate,totalamount,status);
				orderHistoryList.add(e);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderHistoryList;
	}

}
