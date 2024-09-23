package com.foodDelivery.model;

public class User {
	//here userid if you want you can keep otherwise no need..we are not here using userid because it is auto generated
	//good practice is you can use userid also here...
	//it is not necessary to use it but you always keep it ..in future whenever you need to retrive the user based on the userid then we will use this particcular field
	private int userid;
	private String username;
	private String password;   //store hashed password
	private String email;
	private String address;
	private String role;
	//in sql query it automatically take the current date and time so you no need to write java code for that(createdate no need)
	//whenever as a user login,it will actually come and check whether the username and password is matching or not..whenever it matches at the same time the sql query is make sure the date and time is updated over therre..so no need java code for the thats the reasin why here we didnt mention lastlogindate..

	public User(int userid, String username, String password, String email, String address, String role) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
	}
	public User() {
		
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
}
