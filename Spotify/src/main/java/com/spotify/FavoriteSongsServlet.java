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

@WebServlet("/FavoriteSongsServlet")
public class FavoriteSongsServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/spotify";
    private static final String USER = "root";
    private static final String PASS = "Vengat@12345";

    // Inner class representing a favorite song
    class FavoriteSongs {
        List<Integer> songs = new ArrayList<>();

        public FavoriteSongs(List<Integer> songs) {
            this.songs = songs;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        List<Integer> favSongs = new ArrayList<>();
        String sql = "SELECT SONG1, SONG2, SONG3, SONG4, SONG5, SONG6, SONG7, SONG8, SONG9, SONG10 FROM FAVSONG WHERE EMAIL = ?";
        
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, email);  // Use parameterized query to avoid SQL injection
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Fetch the songs from the columns
                    for (int i = 1; i <= 10; i++) {
                        int songId = rs.getInt(i);
                        if (songId != 0) {  // Assuming 0 means no song
                            favSongs.add(i);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list to JSON and send it to the client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(new FavoriteSongs(favSongs));
        out.write(json);
        out.close();
    }
}
