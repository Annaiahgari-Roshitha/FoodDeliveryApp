package com.foodDelivery.dao;

import java.util.List;

import com.foodDelivery.model.User;
//here we are creating interfaces and their abstract methods......
public interface UserDaoo {
	void addUser(User user);
	User getUser(int userId);
	int updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	User getUserByUsername(String username);
}
