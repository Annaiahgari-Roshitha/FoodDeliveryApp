package com.foodDelivery.daoIMP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.UserDaoo;
import com.foodDelivery.model.User;

public class UserDaoImpl implements UserDaoo {
	
	private static Connection connection=null;
	private static PreparedStatement preparedstatement = null;
	private static Statement statement = null;
	private static ResultSet res=null;
	
	private final static String INSERT_QUERY = "insert into `user`(UserId,Username,password,Email,Address,Role) values (?,?,?,?,?,?)";
	private final static String SELECT_QUERY = "select * from `user` where UserId=?";
	private final static String UPDATE_QUERY = "update `user` set `Username`=?, `password`=? , `Email`=?, `Address`=?, `Role`=? where `UserId`=?";
	private final static String DELETE_QUERY = "delete from `user` where `UserId`=?";
	private final static String SELECT_ALL_QUERY = "select * from `user`";
	private final static String QUERY = "select * from `user` where `Username`=?";
	
	public UserDaoImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddeliveryapp","root","Roshitha@1234");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		try {
			preparedstatement = connection.prepareStatement(INSERT_QUERY);
			preparedstatement.setString(1, user.getUsername());
			preparedstatement.setString(2, user.getPassword());
			preparedstatement.setString(3, user.getEmail());
			preparedstatement.setString(4, user.getAddress());
			preparedstatement.setString(5, user.getRole());
			
			int i = preparedstatement.executeUpdate();
			
//			if (i > 0) {
//	            System.out.println("User added successfully.");
//	        } else {
//	            System.out.println("Failed to add user.");
//	        }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {
		
		try {
			preparedstatement = connection.prepareStatement(SELECT_QUERY);
			preparedstatement.setInt(1,userId);
			res = preparedstatement.executeQuery();
			if(res.next()) {
				int id=res.getInt("userId");
				String name=res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String address  = res.getString("address");
				String role = res.getString("role");
				return new User(id,name,password,email,address,role);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUser(User user) {
		
		try {
			preparedstatement = connection.prepareStatement(UPDATE_QUERY);
			preparedstatement.setString(1, user.getUsername());
			preparedstatement.setString(2, user.getPassword());
			preparedstatement.setString(3, user.getEmail());
			preparedstatement.setString(4, user.getAddress());
			preparedstatement.setString(5, user.getRole());
			preparedstatement.setInt(6, user.getUserid());
			return preparedstatement.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteUser(int userId) {
		
		try {
			preparedstatement = connection.prepareStatement(DELETE_QUERY);
			preparedstatement.setInt(1, userId);
			preparedstatement.executeUpdate();
			System.out.println("User Deleted Successfully. ");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		ArrayList<User> userList= new ArrayList<User>();
		try {
			statement = connection.createStatement();
			res =statement.executeQuery(SELECT_ALL_QUERY);
			while(res.next()) {
				int id=res.getInt("userId");
				String name=res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String address  = res.getString("address");
				String role = res.getString("role");
				
				User u=new User(id,name,password,email,address,role);
				userList.add(u);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User getUserByUsername(String username) {
		
		User user = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(QUERY)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserid(rs.getInt("user_id")); // Adjust column names as necessary
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return null;
	}

}
