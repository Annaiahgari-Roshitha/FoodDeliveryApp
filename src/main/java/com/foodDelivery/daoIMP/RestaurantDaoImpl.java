package com.foodDelivery.daoIMP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.RestaurantDao;
import com.foodDelivery.model.Restaurant;


public class RestaurantDaoImpl implements RestaurantDao {
	
	private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    
    private final static String INSERT_QUERY = "insert into `restaurant` (RestaurantId,Name,CuisineType,DeliveryTime,Address,AdminUserID,Rating,ISActive,ImagePath) values (?,?,?,?,?,?,?,?,?)";
    private final static String SELECT_QUERY = "select * from `restaurant` where RestaurantId = ?";
	private final static String UPDATE_QUERY = "update `restaurant` set Name=?, CuisineType=?, DeliveryTime=?, Address=?, AdminUserID=?, Rating=?, ISActive=?, ImagePath=? where restaurantId=?";
	private final static String DELETE_QUERY = "delete from `restaurant` where RestaurantId=?";
	private final static String SELECT_ALL_QUERY = "select * from `restaurant`";

	public RestaurantDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp", "root", "Roshitha@1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try {
			preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, restaurant.getRestaurantId());
			preparedStatement.setString(2, restaurant.getName());
			preparedStatement.setString(3, restaurant.getCuisineType());
			preparedStatement.setInt(4, restaurant.getDeliveryTime());
			preparedStatement.setString(5, restaurant.getAddress());
			preparedStatement.setInt(6, restaurant.getAdminUserId());
			preparedStatement.setDouble(7, restaurant.getRating());
			preparedStatement.setBoolean(8, restaurant.isActive());
			preparedStatement.setString(9, restaurant.getImagePath());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		try {
			preparedStatement = connection.prepareStatement(SELECT_QUERY);
			preparedStatement.setInt(1, restaurantId);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int resId = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String type = resultSet.getString("cuisineType");
				int deliveryTime = resultSet.getInt("deliveryTime");
				String address = resultSet.getString("address");
				int adminUserId = resultSet.getInt("adminUserId");
				Double rating = resultSet.getDouble("rating");
				Boolean isActive = resultSet.getBoolean("isActive");
				String imagePath = resultSet.getString("imagePath");
				
				return new Restaurant(resId,name,type,deliveryTime,address,adminUserId,rating,isActive,imagePath);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		try {
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getCuisineType());
            preparedStatement.setInt(3, restaurant.getDeliveryTime());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setInt(5, restaurant.getAdminUserId());
            preparedStatement.setDouble(6, restaurant.getRating());
            preparedStatement.setBoolean(7, restaurant.isActive());
            preparedStatement.setString(8, restaurant.getImagePath());
            preparedStatement.setInt(9, restaurant.getRestaurantId());
            preparedStatement.executeUpdate();
            System.out.println("Restaurant updated successfully.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		
		try {
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, restaurantId);
			preparedStatement.executeUpdate();
			System.out.println("restaurant deleted Successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
//		if(connection == null) {
//			System.out.println("Database connection is not established");
//			return new ArrayList<>();
//		}
		
		List<Restaurant> restaurantList = new ArrayList<>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			
			
			while(resultSet.next()) {
				int resId = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String type = resultSet.getString("cuisineType");
				int deliveryTime = resultSet.getInt("deliveryTime");
				String address = resultSet.getString("address");
				int adminUserId = resultSet.getInt("adminUserId");
				Double rating = resultSet.getDouble("rating");
				Boolean isActive = resultSet.getBoolean("isActive");
				String imagePath = resultSet.getString("imagePath");
				
				Restaurant e=new Restaurant(resId,name,type,deliveryTime,address,adminUserId,rating,isActive,imagePath);
				restaurantList.add(e);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return restaurantList;
	}

}
