package com.jdbc.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.Timestamp;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.jdbc.config.Config;
import com.jdbc.models.Administrator;
import com.jdbc.models.Participation;
import com.jdbc.models.User;
import com.jdbc.controllers.ParticipationController;
import com.jdbc.controllers.UserController;



public class AdminController {
	
	Config config;
	
	Connection connection;
	
	
	public AdminController() throws ClassNotFoundException, SQLException {
		
		config = new Config("jdbc:mysql://localhost/youcode_talent","root","1234");
		
		connection = config.connect();
	}
	
	
	
	public void adminConnected() throws SQLException {
	
	
	
	String sqlString = "Update admin_session SET  is_connected=true WHERE id=15970010";
	PreparedStatement statement = connection.prepareStatement(sqlString);
	statement.executeUpdate();
	System.out.println("Admin Logged in succesfully");
}

	
	public ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {


		ArrayList<User> usersList = new ArrayList<>();

		String sqlString = "SELECT * FROM user";
		PreparedStatement statement = connection.prepareStatement(sqlString);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			User user = new User(resultSet.getLong("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone"));
			usersList.add(user);

		}

		for (User list : usersList) {
			System.out.println(list.toString());
		}

		return usersList;

	}
	
	public ArrayList<Participation> getParticipations() throws SQLException {

		ArrayList<Participation> participationList = new ArrayList<>();

		String sqlString = "SELECT * FROM participation";
		PreparedStatement statement = connection.prepareStatement(sqlString);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Participation participation = new Participation(resultSet.getLong("id_user"),
					resultSet.getLong("id_category"), resultSet.getString("description"), resultSet.getTimestamp("show_start_time"),
					resultSet.getTimestamp("show_end_time"),resultSet.getString("attached_file"),resultSet.getBoolean("is_accepted"));
			
			participationList.add(participation);

		}
		
		
		for (Participation list : participationList) {
            System.out.println(list.toString());
        }



		return participationList;
	}
	
	
	public Participation getParticipationByEmail(String email) throws SQLException {

		Participation participation  = new Participation();
	
		String sqlString = "SELECT * FROM participation JOIN user ON participation.id_user=user.id WHERE email=?";
		PreparedStatement statement = connection.prepareStatement(sqlString);
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();

		if(resultSet.next()) {
		
			participation.setId_user(resultSet.getLong("id_user"));
			participation.setId_category(resultSet.getLong("id_category"));
			participation.setDescription(resultSet.getString("description"));
			participation.setShow_start_time(resultSet.getTimestamp("show_start_time"));
			participation.setShow_end_time(resultSet.getTimestamp("show_end_time"));
			participation.setAttached_file(resultSet.getString("attached_file"));
			participation.setIs_accepted(resultSet.getBoolean("is_accepted"));

		
		
	}else {
		
		System.out.println("Please Enter a valid user email !");
		
		
	}
		
	
		return participation;

}
	
public void validateParticipation(int userid , int catid , int action) throws SQLException, AddressException, MessagingException {
		
		
		 
		
		
	 
		
	if(action == 1) {
		String email;
			String query = "Update participation SET  is_accepted=true WHERE id_user="+userid+" AND id_category="+catid;
             PreparedStatement statement =connection.prepareStatement(query);
             statement.executeUpdate();
             
            String query1 = "SELECT * FROM participation JOIN user ON participation.id_user=users.id_user WHERE participation.user_id="+userid+" AND id_category="+catid;
     		PreparedStatement statement1 = connection.prepareStatement(query1);
     		ResultSet resultSet = statement1.executeQuery();
     		
     		if(resultSet.next()) {
     			email = resultSet.getString("email");
                String server = "smtp.mailtrap.io";
                String port = "587";
                String username = "079b6089ea7cfa";
                String password = "e03c580b651d5f";
                String subject = "congratulatiodns";
                String msg = "congratulations";
                SendMail(server,port,username,password,email,subject,msg);
    			
    		}
     		
            System.out.println("Participation Accepted !");
            
		}else if(action == 0) {
			
			System.out.println("Participation Denied !");
		}
	
		
	
		
	}

public void SendMail(String server,String port,String username,String password,String to,
        String subject,String msg) throws AddressException, MessagingException {
    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.STARTTLS.enable", "true");
    prop.put("mail.smtp.host", server);
    prop.put("mail.smtp.port", port);

    Session session = Session.getInstance(prop, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

        try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
          Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        Transport.send(message);
        }catch (MessagingException e) {e.printStackTrace();}
}
	
}
