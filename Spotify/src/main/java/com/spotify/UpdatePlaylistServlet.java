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
@WebServlet("/updatePlaylist")
public class UpdatePlaylistServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read data from request
        String requestBody = request.getReader().lines().collect(Collectors.joining());
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        String playlistName = jsonObject.get("playlistName").getAsString();
        int songno = Integer.parseInt(jsonObject.get("songno").getAsString());
        boolean isFavorite = jsonObject.get("isFavorite").getAsBoolean();

        // Create the column name based on songno (e.g., SONG_1, SONG_2, etc.)
        String columnName = "SONG" + songno;

        // Update database based on isFavorite value
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify", "root", "Vengat@12345")) {
            String sql = "UPDATE PLAYLIST SET " + columnName + " = ? WHERE EMAIL = ? and PLAYLISTNAME = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, isFavorite ? 1 : 0);  // Set 1 if favorite, otherwise 0
            HttpSession session = request.getSession();
            stmt.setString(2, (String) session.getAttribute("email"));
            stmt.setString(3, playlistName);
            int rowsAffected = stmt.executeUpdate();

            // Send response back to the client
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("message", rowsAffected > 0 ? "Success" : "Failed");
            response.getWriter().write(jsonResponse.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
