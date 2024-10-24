package com.spotify;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/fetchPlaylistname")
public class PlaylistNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Retrieve email from session
        HttpSession session = request.getSession();  // Retrieve existing session
        String email = (String) session.getAttribute("email");
        
        ArrayList<String> playlistNames = new ArrayList<>();
        
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/spotify", "root", "Vengat@12345");
            
            // SQL Query to fetch all playlist names for the given email
            String query = "SELECT PLAYLISTNAME FROM PLAYLIST WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            // Loop through the result set and collect all playlist names
            while (rs.next()) {
                playlistNames.add(rs.getString("PLAYLISTNAME"));
            }
            
            // Convert the playlist names to a JSON array
            JsonArray jsonArray = new JsonArray();
            for (String name : playlistNames) {
                jsonArray.add(name);
            }
            
            // Send the playlist names as a JSON response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.add("playlists", jsonArray);
            out.print(jsonResponse.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
