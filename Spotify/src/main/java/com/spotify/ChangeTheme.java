package com.spotify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@WebServlet("/ChangeTheme")
public class ChangeTheme extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read data from request
        String requestBody = request.getReader().lines().collect(Collectors.joining());
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        int theme = jsonObject.get("theme").getAsInt();

        // Update the theme in the session
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify", "root", "Vengat@12345")) {
            String sql = "UPDATE USER SET Theme = ? WHERE EMAIL = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, theme); 
            HttpSession session = request.getSession();
            stmt.setString(2, (String) session.getAttribute("email"));
            int rowsAffected = stmt.executeUpdate();

        // Send response back to the client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", "Theme updated successfully");
        response.getWriter().write(jsonResponse.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

