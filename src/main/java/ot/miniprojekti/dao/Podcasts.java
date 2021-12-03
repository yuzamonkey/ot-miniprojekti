package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Podcasts {

    private Connection db;

    public Podcasts(String data) throws SQLException {
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
        PreparedStatement stmt = db.prepareStatement("INSERT INTO podcasts (name, title, description) VALUES (?, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, title);
        stmt.setString(3, description);
        stmt.executeUpdate();
        stmt.close();
    }

}
