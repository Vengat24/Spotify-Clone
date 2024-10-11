package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/createPlaylist")  // URL endpoint for the servlet
public class NewPlaylistServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Get the email and playlist name from the request
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String playlistname = request.getParameter("playlistname");

        // Assuming you have a valid database connection setup
        Connection con = null;
        PreparedStatement ps = null;
        String jsonResponse = "";  // This will hold the response

        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");  // Adjust driver if needed
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotify", "root", "Vengat@12345");

            // SQL query to insert playlist data
            String sql = "INSERT INTO PLAYLIST (email, playlistname) VALUES (?, ?);";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, playlistname);

            // Execute the query
            int playlistUpdate = ps.executeUpdate();

            // Check if the insert was successful
            if (playlistUpdate > 0) {
                jsonResponse = "{\"status\":\"success\", \"message\":\"Playlist created successfully.\"}";
            } else {
                jsonResponse = "{\"status\":\"fail\", \"message\":\"Failed to create playlist.\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"status\":\"error\", \"message\":\"Error: " + e.getMessage() + "\"}";
        } finally {
            // Close resources
            try { if (ps != null) ps.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        // Send JSON response back to the client
        out.write(jsonResponse);
        out.flush();
    }
}
