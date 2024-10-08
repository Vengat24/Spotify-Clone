package com.spotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	
	public boolean checkUser(String email,String password) {
		try {
			String sql = "select * from user where email = ? and password = ?";
			Connection con = Connections.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}