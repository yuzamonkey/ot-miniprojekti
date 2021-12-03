package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Blogs {
    private Connection db;

    public Blogs(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS blogposts \n"
                    + "(id SERIAL PRIMARY KEY, title TEXT, author TEXT, url TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String title, String author, String url) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO blogposts (title, author, url) VALUES (?, ?, ?)");
        stmt.setString(2, title);
        stmt.setString(1, author);
        stmt.setString(3, url);
        stmt.executeUpdate();
        stmt.close();
    }
}
