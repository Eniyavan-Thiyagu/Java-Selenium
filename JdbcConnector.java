package com.Selenium.FileHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcConnector {
      public static void main(String[] args) {
      String jdbcURL = "jdbc:mysql://localhost:3306/testdb";
      String username = "root";
      String password = "Gomathi@25";
      
      try {
    	  //Connection to DB 
    	  Connection conn = DriverManager.getConnection(jdbcURL,username, password);
    	  System.out.println("Connected to mysql");
    	  
    	  //Create a Table
    	  String createTable ="CREATE TABLE IF NOT EXISTS users ("  + " id INT AUTO_INCREMENT PRIMARY KEY," + "name VARCHAR(50),"+"email VARCHAR(100)"+")";
    	  conn.createStatement().execute(createTable);
    	  System.out.println("TABLE CREATED");
    	  
    	  //Insert Data
    	  String insertSQL = "INSERT INTO users(name,email) VALUES(?,?)";
    	  PreparedStatement stmt =conn.prepareStatement(insertSQL);
    	  stmt.setString(1, "Jhon Doe");
    	  stmt.setString(2, "jhon@email.com");
    	  stmt.executeUpdate();
    	  System.out.println("Data Inserted");
    	  
    	  //Read Data
    	  ResultSet rs =conn.createStatement().executeQuery("Select * From users");
    	  while(rs.next()) {
    		  System.out.println("User: " +rs.getString("name") + "| Email:" + rs.getString("email"));
    		  
    	  }
    	  
    	  //Close Connection
    	  conn.close();
    	  System.out.println("connection Closed");
    	  
      }catch(Exception e) {
    	  e.printStackTrace();
     
    	  
    	  
      }
      }
      
}
