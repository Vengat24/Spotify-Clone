package com.spotify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/GetSongs")
public class GetSongsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set up database connection and fetch the songs from the database
        List<Song> songs = new ArrayList<>();
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb", "username", "password");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM songs";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Create a new song object for each row in the result set
                Song song = new Song();
                song.setName(rs.getString("name"));
                song.setArtist(rs.getString("artist"));
                song.setImgLink(rs.getString("imglink"));
                song.setAudioLink(rs.getString("audiolink"));
                song.setLength(rs.getInt("length"));

                songs.add(song);  // Add the song to the list
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list of Song objects to JSON using Gson
        Gson gson = new Gson();
        String jsonSongs = gson.toJson(songs);  // This converts the list to a JSON string

        // Set the response type to JSON and write the JSON string to the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Hello");
        PrintWriter out = response.getWriter();
        out.write(jsonSongs);
        out.close();
    }
}


