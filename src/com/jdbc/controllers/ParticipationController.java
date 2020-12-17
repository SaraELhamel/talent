package com.jdbc.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.regex.Pattern;

import java.util.Scanner;

import com.jdbc.config.Config;



public class ParticipationController {
	
	Config config;
	Connection connection;
	Scanner scanner;

public ParticipationController() throws ClassNotFoundException, SQLException {
		
		config = new Config("jdbc:mysql://localhost/youcode_talent","root","1234");
		connection = config.connect();
		scanner = new Scanner(System.in);
	}

String timeRegex = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]";

public void addParticipation(long Id) throws SQLException {

    System.out.println("Choose your category:");

    String sqlString = "SELECT * FROM category";
    PreparedStatement statement = connection.prepareStatement(sqlString);
    ResultSet resultSet = statement.executeQuery();

    while(resultSet.next()) {
        System.out.println(resultSet.getLong("id")+". "+resultSet.getString("name"));
    }
    
    System.out.println("Select category by id :");
    
    String categoryStr = scanner.nextLine();
	long category = Long.parseLong(categoryStr);
	while(category > 6 || category < 1) {
		System.out.println("Please Select a vlid id !");
		categoryStr = scanner.nextLine();
		category = Long.parseLong(categoryStr);
		
    
}
	
	
	System.out.println("Enter a description for your talent:");
	String desc = scanner.nextLine();
	while(desc.length() < 4) {
		System.out.println("Please enter a longer description !");
		desc = scanner.nextLine();
		
	}
	
	System.out.println("Enter the start time of your show: (yyy-mmm-dd h:m:s)");
	String startTime = scanner.nextLine();
	Timestamp startTimestamp = null;
	
	if(startTime.matches(timeRegex)) {
		 startTimestamp = Timestamp.valueOf(startTime);
	}else {
		while(!startTime.matches(timeRegex)) {
		System.out.println("Please Enter a Valid Date !");
		startTime = scanner.nextLine();

		}
 
	
		
}
	
	System.out.println("Enter the End time of your show: (yyy-mmm-dd h:m:s)");
	String endTime = scanner.nextLine();
	Timestamp endTimestamp = null;
	
	if(endTime.matches(timeRegex)) {
		 endTimestamp = Timestamp.valueOf(endTime);
	}else {
		while(!endTime.matches(timeRegex)) {
		System.out.println("Please Enter a Valid Date !");
		endTime = scanner.nextLine();

		}

}
	System.out.println("Enter the path of your attached file:");
	String file = scanner.nextLine();
	
	boolean is_accepted = false;
	
	String query = "INSERT into participation (id_user ,id_category, description, show_start_time, show_end_time,attached_file,is_accepted) values(?,?,?,?,?,?,?)";
	  PreparedStatement statement11 = connection.prepareStatement(query);
		statement11.setLong(1, Id);
		statement11.setLong(2, category);
		statement11.setString(3, desc);
		statement11.setTimestamp(4, startTimestamp);
		statement11.setTimestamp(5, endTimestamp );
		statement11.setString(6, file);
		statement11.setBoolean(7, is_accepted );
		
		statement11.executeUpdate();
	 
	System.out.println("Thank You ! Please watch your email .");
}
}
