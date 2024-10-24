package com.spotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginDao {
	
	
	public boolean checkUser(String email,String password,HttpSession session,HttpServletRequest request) {
		try {
			String sql = "select * from user where email = ? and password = ?";
			Connection con = Connections.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				session.setAttribute("username", rs.getString(2));
				session.setAttribute("gender", rs.getString(5));
				session.setAttribute("dob",rs.getDate(4));
				return true;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean getTheme(String email) {
		try {
			String sql = "select theme from user where email = ?";
			Connection con = Connections.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				int result = rs.getInt(1);	
				return result == 1;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}