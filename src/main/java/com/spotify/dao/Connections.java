package com.spotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	static String dbUser = "root";
	static String dbPassword = "Vengat@12345";
	static String url = "jdbc:mysql://localhost:3306/Spotify";
	
	
	static Connection con;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con == null) {			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,dbUser,dbPassword);
		}
		return con;
	}
}
