package ot.miniprojekti.dao;

import ot.miniprojekti.domain.Video;

import java.sql.*;
import java.util.ArrayList;

public class Videos {

    private Connection db;
    private String data;

    public Videos(String data) throws SQLException {
        this.data = data;
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS videos \n"
                    + "(id INTEGER PRIMARY KEY, title TEXT, url TEXT, comment TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String title, String url, String comment) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO videos (title, url, comment) VALUES (?, ?, ?)");
        stmt.setString(1, title);
        stmt.setString(2, url);
        stmt.setString(3, comment);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Video> getAll() throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        ArrayList<Video> videos = new ArrayList<Video>();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM videos");
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String url = result.getString("url");
            String comment = result.getString("comment");
            videos.add(new Video(id, title, url, comment));
        }
        db.close();

        return videos;
    }
}
