package com.spotify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.spotify.dao.SignupDao;

@WebServlet("/signup2")
public class Signup2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String name = request.getParameter("username");
		String gender = request.getParameter("gender");
		Date sqlDate = null;
		try {
			session.setAttribute("username", name);
			session.setAttribute("gender", gender);
		    String dateString = year + "-" + getMonth(month) + "-" + date;
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    java.util.Date utilDate = sdf.parse(dateString);
		    sqlDate = new Date(utilDate.getTime());
		    System.out.println(sqlDate);
		    session.setAttribute("dob",sqlDate);	
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		try {
			SignupDao.addUser(email, password, name, gender, sqlDate);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
	}
	
	private int getMonth(String month) {
	    switch (month.toLowerCase()) { // Convert input to lower case for case-insensitive comparison
	        case "january":   return 1;
	        case "february":  return 2;
	        case "march":     return 3;
	        case "april":     return 4;
	        case "may":       return 5;
	        case "june":      return 6;
	        case "july":      return 7;
	        case "august":    return 8;
	        case "september": return 9;
	        case "october":   return 10;
	        case "november":  return 11;
	        case "december":  return 12;
	        default:         return -1; // Return -1 for invalid month input
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
