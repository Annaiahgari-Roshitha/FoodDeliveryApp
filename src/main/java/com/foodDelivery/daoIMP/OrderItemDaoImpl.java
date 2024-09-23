package com.foodDelivery.daoIMP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.OrderItemDao;
import com.foodDelivery.model.OrderItem;

public class OrderItemDaoImpl implements OrderItemDao {
	
	private static Connection connection = null;
	private static PreparedStatement preparestatement = null;
	private static Statement statement = null;
	private static ResultSet res = null;
	
	
	private final static String INSERT_QUERY = "insert into `orderitems` (OrderID, MenuID, Quantity, ItemTotal) values(?,?,?,?)";
	private final static String SELECT_QUERY ="select * from `orderitems` where OrderItemID=?";
	private final static String UPDATE_QUERY = "update `orderitems` set OrderID=?, MenuID=?, Quantity=?, ItemTotal=? where OrderItemID=?";
	private final static String DELETE_QUERY = "delete from `orderitems` where OrderItemID=?";
	private final static String SELECT_BY_ORDER_QUERY = "select * from `orderitems` where OrderID=?";
	
	public OrderItemDaoImpl() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "Roshitha@1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		
		try {
			preparestatement = connection.prepareStatement(INSERT_QUERY);
			preparestatement.setInt(1, orderItem.getOrderId());
			preparestatement.setInt(2, orderItem.getMenuId());
			preparestatement.setInt(3, orderItem.getQuantity());
			preparestatement.setDouble(4, orderItem.getItemTotal());
			
			int i = preparestatement.executeUpdate();
			System.out.println("insertion completed successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		
		try {
			preparestatement = connection.prepareStatement(SELECT_QUERY);
			preparestatement.setInt(1, orderItemId);
			res = preparestatement.executeQuery();
			
			if(res.next()) {
				int orderitemid = res.getInt("OrderItemID");
				int orderid = res.getInt("OrderID");
				int menuid = res.getInt("MenuID");
				int quantity = res.getInt("Quantity");
				Double itemtotal= res.getDouble("ItemTotal");
				
				return new OrderItem(orderitemid,orderid,menuid,quantity,itemtotal);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		
		try {
			preparestatement = connection.prepareStatement(UPDATE_QUERY);
			preparestatement.setInt(1, orderItem.getOrderId());
			preparestatement.setInt(2, orderItem.getMenuId());
			preparestatement.setInt(3, orderItem.getQuantity());
			preparestatement.setDouble(4, orderItem.getItemTotal());
			preparestatement.setInt(5, orderItem.getOrderItemId());
			
			preparestatement.executeUpdate();
			System.out.println("Order item updated Successfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		
		try {
			preparestatement = connection.prepareStatement(DELETE_QUERY);
			preparestatement.setInt(1, orderItemId);
			preparestatement.executeUpdate();
			System.out.println("order item deleted successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(int orderId) {
		
		List<OrderItem> orderItemList = new ArrayList<>();
		
		try {
			preparestatement =connection.prepareStatement(SELECT_BY_ORDER_QUERY);
			preparestatement.setInt(1, orderId);
			res = preparestatement.executeQuery();
			
			while(res.next()) {
				int orderitemid = res.getInt("OrderItemID");
				int orderid = res.getInt("OrderID");
				int menuid = res.getInt("MenuID");
				int quantity = res.getInt("Quantity");
				Double itemtotal= res.getDouble("ItemTotal");
				
				OrderItem e = new OrderItem(orderitemid,orderid,menuid,quantity,itemtotal);
				orderItemList.add(e);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderItemList;
	}

}
