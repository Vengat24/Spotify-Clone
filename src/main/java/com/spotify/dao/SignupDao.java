package com.spotify.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDao {

	public static boolean checkUser(String email) throws ClassNotFoundException, SQLException {
	    Connection con = Connections.getConnection();
	    String sql = "SELECT * FROM user WHERE email = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, email);
	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	        return false; // User with this email exists
	    }
	    return true; // No user with this email
	}
	public static void addUser(String email, String password, String username, String gender, Date date) throws ClassNotFoundException, SQLException {
	    Connection con = Connections.getConnection();

	    // Insert user into the 'user' table
	    String sql = "INSERT INTO user (email, username, password, dob, gender) VALUES (?, ?, ?, ?, ?);";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, email);
	    ps.setString(2, username);
	    ps.setString(3, password);
	    ps.setDate(4, date);
	    ps.setString(5, gender);
	    int rs = ps.executeUpdate();

	    // Add user to 'favsong' table (assuming the favsong table has columns like 'email' and song favorites)
	    sql = "INSERT INTO favsong (email) VALUES (?);";
	    ps = con.prepareStatement(sql);
	    ps.setString(1, email);
	    int favSongUpdate = ps.executeUpdate(); // Use executeUpdate for INSERT

	    System.out.println("User added successfully");
	}
	
	public static String getUserName(String email) throws ClassNotFoundException, SQLException {
		Connection con = Connections.getConnection();
	    String sql = "SELECT USERNAME FROM USER WHERE EMAIL = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, email);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
		return rs.getString(1);
	}


	

}
