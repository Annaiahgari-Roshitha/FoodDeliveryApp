package com.foodDelivery.daoIMP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.MenuDao;
import com.foodDelivery.model.Menu;
import com.foodDelivery.model.Restaurant;

public class MenuDaoImpl implements MenuDao {
	
	private static Connection connection = null;
	private static PreparedStatement preparestatement = null;
	private static Statement statement = null;
	private static ResultSet res = null;
	
	private final static String INSERT_QUERY = "insert into `menu` (RestaurantID,ItemName,Description,Price,ISAvailable) values (?,?,?,?,?)";
	private final static String SELECT_QUERY = "select * from `menu` where MenuID = ?";
	private final static String UPDATE_QUERY = "update `menu` set RestaurantID=?, ItemName=?, Description=?, Price=?, IsAvailable=? where MenuID=?";
	private final static String DELETE_QUERY = "delete from `menu` where MenuID=?";
	private final static String SELECT_ALL_QUERY = "select * from `menu` where RestaurantID=?";

	public MenuDaoImpl() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "Roshitha@1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void addMenu(Menu menu) {
		
		try {
			preparestatement = connection.prepareStatement(INSERT_QUERY);
			preparestatement.setInt(1, menu.getRestaurantId());
			preparestatement.setString(2, menu.getItemName());
			preparestatement.setString(3, menu.getDescription());
			preparestatement.setDouble(4, menu.getPrice());
			preparestatement.setBoolean(5, menu.isAvailable());
			
			int i = preparestatement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {
		
		try {
			preparestatement = connection.prepareStatement(SELECT_QUERY);
			preparestatement.setInt(1, menuId);
			
			res = preparestatement.executeQuery();
			if (res.next()) {
				int menuid = res.getInt("MenuID");
				int resid = res.getInt("RestaurantID");
				String itemname = res.getString("ItemName");
				String description = res.getString("Description");
				Double price = res.getDouble("Price");
				Boolean isavailable = res.getBoolean("IsAvailable");
				
				return new Menu(menuid,resid,itemname,description,price,isavailable);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		try {
			preparestatement = connection.prepareStatement(UPDATE_QUERY);
			preparestatement.setInt(1, menu.getRestaurantId());
			preparestatement.setString(2, menu.getItemName());
			preparestatement.setString(3, menu.getDescription());
			preparestatement.setDouble(4, menu.getPrice());
			preparestatement.setBoolean(5, menu.isAvailable());
			preparestatement.setInt(6, menu.getMenuId());
			
			preparestatement.executeUpdate();
			System.out.println("Menu updated succesfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteMenu(int menuId) {
		
		try {
			preparestatement = connection.prepareStatement(DELETE_QUERY);
			preparestatement.setInt(1, menuId);
			preparestatement.executeUpdate();
			System.out.println("Menu deleted succesfully");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {
		
		ArrayList<Menu> restaurantList = new ArrayList<Menu>();
		
		try {
			preparestatement = connection.prepareStatement(SELECT_ALL_QUERY);
			preparestatement.setInt(1, restaurantId);
			res = preparestatement.executeQuery();
			
			while(res.next()) {
				int menuid = res.getInt("MenuID");
				int resid = res.getInt("RestaurantID");
				String itemname = res.getString("ItemName");
				String description = res.getString("Description");
				Double price = res.getDouble("Price");
				Boolean isavailable = res.getBoolean("IsAvailable");
				
				Menu e=new Menu(menuid,resid,itemname,description,price,isavailable);
				restaurantList.add(e);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return  restaurantList;
	}

}
