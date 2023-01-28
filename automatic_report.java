package net.codejava;
 
import java.io.*;
import java.sql.*;
import java.util.*;

public class automatic_report.java {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/studentד";
		
        String username = "root";
        String password = "idoyt1234";
		
        Connection connection = null;
 
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
			
			Statement stmt = connection.createStatement();
            ResultSet rs = null;
			
			int option = 0;
			
			System.out.println("HELLO SIMA! YOU FU@%% Mo#@***^@#, here is your automatic report");
			
			while (option != 8){
				
				System.out.println("Press 1 to return all of the students average");
                System.out.println("Press 2 to return only male students average");
                System.out.println("Press 3 to return only female students average");
                System.out.println("Press 4 to return the average height of the students that is 200 cm or taller and have a purple car");
                System.out.println("Press 5 to return the id's of all the friends of a specific student and all their friends id's too");
                System.out.println("Press 6 to return the percentage of students in different social statuses");
                System.out.println("Press 7 to return the grades average of a specific student");
                System.out.println("Press 8 to exit the program");
				
				int option = getOptionNumber();
				if(option == 8){
					System.out.println("goodbye!");
					break;
				}
				
				switch (option) {
                    case 1:
						rs = stmt.executeQuery("SELECT AVG(grades_avg) FROM students;");
						
                        if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("The average grade of the school is " + ret);
                        }

                        rs.close();
                        break;
                    case 2:
                        rs = stmt.executeQuery("SELECT AVG(grades_avg) FROM students WHERE gender = 'Male';");
						
                        if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("The average grade of the school male students is " + ret);
                        }

                        rs.close();
                        break;
					case 3:
						rs = stmt.executeQuery("SELECT AVG(grades_avg) FROM students WHERE gender = 'Female';");
						
                        if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("The average grade of the school female students is " + ret);
                        }

                        rs.close();
                        break;
					case 4:
						rs = stmt.executeQuery("SELECT AVG(cm_height) FROM students WHERE cm_height >= 200 AND car_color = 'Purple';");
                        
						if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("The average hieght of the students whose height is 200 cm or more and hava a purple car is" + ret);
                        }
    
                        rs.close();
                        break;
					case 5:
						ArrayList<Integer> final = new ArrayList<Integer>();
                        ArrayList<Integer> new_friends = new ArrayList<Integer>();
                        System.out.println("Enter id:");
                        int id = scan.nextInt();
                        scan.nextLine();
						
                        rs = stmt.executeQuery("SELECT friend_id, other_friend_id FROM friendships WHERE friend_id = " + id + " OR other_friend_id = " + id);
                        while (rs.next()) {
                            int friend_id = rs.getInt("friend_id");
                            int other_friend_id = rs.getInt("other_friend_id");
                            if (friend_id != id) {
                                final.add(friend_id);
                            } 
							else {
                                final.add(other_friend_id);
                            }
                        }

                        for (Integer ID : final) {
                            rs = stmt.executeQuery("SELECT friend_id, other_friend_id FROM friendships WHERE friend_id = " + ID + " OR other_friend_id = " + ID);
                            while (rs.next()) {
                                int friend_id = rs.getInt("friend_id");
                                int other_friend_id = rs.getInt("other_friend_id");
								
                                if (!final.contains(friend_id) && friend_id != ID && friend_id != id) {
                                    new_friends.add(friend_id);
                                }
                                if (!final.contains(other_friend_id) && other_friend_id != ID && other_friend_id != id) {
                                    new_friends.add(other_friend_id);
                                }
                            }
                        }

                        final.addAll(new_friends);
                        System.out.println("All friends id's:\n" + final);
    
                        rs.close();
                        break;
					case 6:
						
						rs = "SELECT COUNT(DISTINCT id) / (SELECT COUNT(*) FROM friendships) * 100 AS popular_percentage FROM friendships WHERE friend_id IS NOT NULL AND other_friend_id IS NOT NULL;";
						
						if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("Percentage of popular students is" + ret + "%");
                        }
						
						rs = "SELECT COUNT(DISTINCT id) / (SELECT COUNT(*) FROM friendships) * 100 AS lonely_percentage FROM friendships WHERE friend_id IS NOT NULL AND other_friend_id IS NOT NULL;";
						
						if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("Percentage of lonely students is" + ret + "%");
                        }
						
						rs.close();
                        break;
					case 7:
						System.out.println("Enter id:");
                        int id = scan.nextInt();
                        scan.nextLine();
                        
                        rs = stmt.executeQuery("SELECT grades_avg FROM view WHERE identification_card = " + id);
                        if (rs.next()) {
                            double ret = rs.getDouble(2);
                            System.out.println("The average grade of the student is: " + ret);
                        }
    
                        rs.close();
                        break;
					default:
						System.out.println("Option number not found, please type again");
						break;
                    }
			}
			
            workbook.close();
			
            statement.executeBatch();
  
            connection.commit();
            connection.close();
 
    }
	
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
}

public static int getOptionNumber(){
	Scanner console = new Scanner(System.in);  
    int num = console.nextInt();  
	return num;
}