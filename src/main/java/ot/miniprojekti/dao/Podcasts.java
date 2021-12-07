package ot.miniprojekti.dao;

import ot.miniprojekti.domain.Podcast;

import java.sql.*;
import java.util.ArrayList;

public class Podcasts {

    private Connection db;
    private String data;

    public Podcasts(String data) throws SQLException {
        this.data = data;
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS podcasts \n"
                    + "(id SERIAL PRIMARY KEY, name TEXT, title TEXT, description TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String name, String title, String description) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO podcasts (name, title, description) VALUES (?, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, title);
        stmt.setString(3, description);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Podcast> getAll() throws SQLException {
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM podcasts");
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            String name = result.getString("name");
            String title = result.getString("title");
            String description = result.getString("description");
            podcasts.add(new Podcast(title, name, description));
        }
        db.close();

        return podcasts;
    }
}
