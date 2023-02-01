package net.codejava;
 
import java.io.*;
import java.sql.*;
import java.util.*;

public class data_transform {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/students";
		
        String username = "root";
        String password = "idoyt1234";
 
        String highschool_FilePath = "C:\\Users\\ido72\\OneDrive\\Desktop\\M\\TalBar\\SQL work\\highschool.csv";
		String friendships_FilePath = "C:\\Users\\ido72\\OneDrive\\Desktop\\M\\TalBar\\SQL work\\highschool_friendships.csv";
 
        int batchSize = 1000;
 
        Connection connection = null;
 
        try{
			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);
	  
			String sql = "INSERT INTO students (id, first_name, last_name, email, gender, ip_address, cm_height, age, has_car, grade, gardes_avg, identification_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			BufferedReader lineReader = new BufferedReader(new FileReader(highschool_FilePath));
				
			String lineText = null;
			int count = 0;
				
			lineReader.readLine();
			
			 while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");

                int id = data[0];
                String first_name = data[1];
				String last_name = data[2];
                String email = data[3];
				String gender = data[4];
                String ip_address = data[5];
				int cm_height = data[6];
				int age = data[7];
                String has_car = data[8];
				String car_color = data[9];
				int garde = data[10];
				double gardes_avg = data[11];
				int identification_card = data[12];

                statement.setInt(1,parseInt(id));
                statement.setString(2,first_name);
                statement.setString(3,last_name);
				statement.setString(4,email);
                statement.setString(5,gender);
				statement.setString(6,ip_address);
                statement.setInt(7,parseInt(cm_height));
				statement.setInt(8,parseInt(age));
				statement.setString(9,has_car);
				if (has_car == "True") statement.setString(10,car_color);
				else statement.setString(10,null);
				statement.setInt(11,parseInt(garde));
				statement.setDouble(12,parseDouble(gardes_avg));
				statement.setInt(13,parseInt(identification_card));

				
                statement.addBatch();
                if(count%batchSize==0){
                    statement.executeBatch();
                }
			 }
			 
			lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data for highschool table has been inserted successfully.");
		}
		
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		try{
			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);
			
			String sql2 = "INSERT INTO friendships (id, friend_id, other_friend_id) VALUES (?, ?, ?);";
			PreparedStatement statement2 = connection.prepareStatement(sql2);		

			BufferedReader lineReader2 = new BufferedReader(new FileReader(friendships_FilePath));
				
			String lineText2 = null;
			int count2 = 0;
				
			lineReader.readLine();
			
			 while ((lineText2=lineReader2.readLine())!=null){
                String[] data2=lineText2.split(",");

                int id2 = data[0];
                int friend_id = data[1];
				int other_friend_id = data[2];

                statement2.setInt(1,parseInt(id2));
                statement2.setInt(2,parseInt(friend_id));
                statement2.setInt(3,parseInt(other_friend_id));

				
                statement2.addBatch();
                if(count2%batchSize==0){
                    statement2.executeBatch();
                }
			 }
			 
			lineReader2.close();
            statement2.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data for friendship table has been inserted successfully.");
		}
		
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
}
}
