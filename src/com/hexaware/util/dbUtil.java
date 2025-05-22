package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbUtil {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/payxpert";
		String user = "root";
		String password = "Hexaware@12345";
		
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection to database Succesful");
			return conn;
		}catch(SQLException e){
			System.out.println("Failed to connect to the database");
			e.printStackTrace();
			return null;
		}
	}
}
