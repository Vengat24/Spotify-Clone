package com.spotify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/PlaylistServlet")
public class PlaylistServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/spotify";
    private static final String USER = "root";
    private static final String PASS = "Vengat@12345";

    // Inner class representing a playlist with songs
    class PlaylistSongs {
        List<Integer> songs = new ArrayList<>();

        public PlaylistSongs(List<Integer> songs) {
            this.songs = songs;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Get playlist name from the request parameter
        String playlistName = request.getParameter("playlistName");
        if (playlistName == null || playlistName.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing or invalid playlist name.");
            return;
        }

        List<Integer> playlistSongs = new ArrayList<>();
        String sql = "SELECT SONG1, SONG2, SONG3, SONG4, SONG5, SONG6, SONG7, SONG8, SONG9, SONG10 " +
                     "FROM PLAYLIST WHERE EMAIL = ? AND PLAYLISTNAME = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, email);  // Set the email
            ps.setString(2, playlistName);  // Set the playlist name

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Fetch the actual song IDs from the columns
                    for (int i = 1; i <= 10; i++) {
                        int songId = rs.getInt(i);
                        if (songId != 0) {  // Assuming 0 means no song
                            playlistSongs.add(i);  // Add the actual song ID
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error retrieving playlist songs.");
            return;
        }

        // Convert the list to JSON and send it to the client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(new PlaylistSongs(playlistSongs));
        out.write(json);
        out.close();
    }
}
