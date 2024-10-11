package com.spotify;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/fetchPlaylistname")
public class PlaylistNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String playlistName = "";
        
        // Retrieve email from session
        HttpSession session = request.getSession();  // Retrieve existing session
            String email = (String) session.getAttribute("email");
                try {
                    // Database connection
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/spotify", "root", "Vengat@12345");
                    
                    // SQL Query to fetch playlist name
                    String query = "SELECT PLAYLISTNAME FROM PLAYLIST WHERE email = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, email);
                    ResultSet rs = ps.executeQuery();
                    
                    if (rs.next()) {
                        playlistName = rs.getString("PLAYLISTNAME");
                    }
                    
                    // Send the playlist name as a JSON response
                    out.print("{\"playlistName\": \"" + playlistName + "\"}");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                }
            }
    }
