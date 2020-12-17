package com.jdbc.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.controllers.AdminController;
import com.jdbc.controllers.ParticipationController;
import com.jdbc.controllers.UserController;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserController user = new UserController();
        AdminController admin = new AdminController();
        ParticipationController participation = new ParticipationController();
        Scanner reader1 = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);
        
        Scanner chara = new Scanner(System.in);
        
      //phone validation
    	String phoneRegex = "^(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}$";
    	//email validation
    	  String emailRegex = "^(.+)@(.+)$";
    	

        while(true) {

            System.out.println("1. S'inscrire autant que condidat");
            System.out.println("2. Se connecter autant qu'administrateur");
            int Choix= reader1.nextInt();
            switch(Choix) {
            case 1:
            	
            	while(true) {
                    System.out.println("Enter your first name, last name, email, numero tel:");
                      String fname = reader2.nextLine();
                      String lname = reader2.nextLine();
                      String email = reader2.nextLine();
                      String phone = reader2.nextLine();
                      if(email.matches(emailRegex) && phone.matches(phoneRegex)) {
                          user.addUser(fname, lname, email, phone);
                          System.out.println("Update your info ? (y/n)");
                          char yn = chara.next().charAt(0);
                          switch(yn) {
                          case 'y':
                        	  System.out.println("Enter Your First name , last Name , email , phone :");
                        	  fname = reader2.nextLine();
                              lname = reader2.nextLine();
                              email = reader2.nextLine();
                              phone = reader2.nextLine();
                              user.updateUser(fname, lname, email, phone, user.id);
                              break;
                          case 'n':
                        	  break;
                          }
                          
                          participation.addParticipation(user.id);
                          break;
                      }else {
                          System.out.println("Please Enter a Valid email or phone number");
                      }
                }
       		  
       		
            	break; 
                 


			case 2:
				
				boolean a = true;
				String adminEmail = "ahmed.mahmoud.admin@gmail.com";
                String adminPswrd = "123456";
                

	        	System.out.println("Email :");
	        	String email = reader2.nextLine();
	        	System.out.println("Password :");
	        	String pswrd = reader2.nextLine();
	        	
	        	if(email.equals(adminEmail) && pswrd.equals(adminPswrd) ) {
	        		admin.adminConnected();
	        		
	        		while(a) {
	        			
	        			
	    				
	        			System.out.println("1 - Display all users");
	                    System.out.println("2 - Display all participations");
	    				System.out.println("3 - Find Participation by email");
	    				System.out.println("4 - Validate Participation");
	    				System.out.println("5 - Log out");
	    				int choixx = reader1.nextInt();
	    				
	    				switch (choixx) {
						case 1:
							
							admin.getUsers();
							
							break;
						case 2:
							
							admin.getParticipations();
						
							break;
						case 3:
							
							System.out.println("Please Enter the email :");
							String emaill = reader2.nextLine();
							System.out.println(admin.getParticipationByEmail(emaill).toString());
							break;
						case 4:
							
							
							System.out.println("Enter the id of the user :");
							int userid = reader1.nextInt();
							System.out.println("Enter the id of the Participation :");
							int catid = reader1.nextInt();
							
							System.out.println("Select action :");
							System.out.println("1. Accept");
							System.out.println("2. Deny");
							int action = reader1.nextInt();
							
							
							
							
							break;
						case 5:
							 a = false;
							break;
						}
	    				
	    				
	    				
	        		}
	        		}else {
	        			
	        			System.out.println("Please Enter a valid Email and Password !");
	        		
	        		}
	        		}
				
                break;
            }

        }

   
}


