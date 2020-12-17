package com.jdbc.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.jdbc.config.Config;
import com.jdbc.models.User;

public class UserController {
	

	Config config;
	Scanner scanner;
	Connection connection;
	public long id;
	
	
	public UserController() throws ClassNotFoundException, SQLException {
		
		config = new Config("jdbc:mysql://localhost/youcode_talent","root","1234");
		scanner = new Scanner(System.in);
		connection = config.connect();
	}

	
	public void addUser(String fname , String lname , String email , String phone) throws SQLException, ClassNotFoundException {
		
		  Random rd = new Random();
	      long id = (long)(rd.nextDouble()*1000000000L);
	      
	     
		
	  	
		  String sqlString = "INSERT into user (id ,first_name, last_name, email, phone) values(?,?,?,?,?)";
		  PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setLong(1, id);
			statement.setString(2, fname);
			statement.setString(3, lname);
			statement.setString(4, email);
			statement.setString(5, phone );
			statement.executeUpdate();
		 
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(phone);
		
		this.id = id;
		
	}
	
	public void updateUser(String fname , String lname , String email , String phone , long id) throws SQLException, ClassNotFoundException {
	      
	     
		
	  	
	      String sqlString = "update  user SET  first_name=?, last_name=?, email=?, phone=? WHERE id=?";
	      java.sql.PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, email);
			statement.setString(4, phone );
			statement.setLong(5, id);
			statement.executeUpdate();
		 
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(phone);
		
	}
	
	
	

}