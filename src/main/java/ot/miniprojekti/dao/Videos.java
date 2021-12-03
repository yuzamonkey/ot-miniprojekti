
package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Videos {
    
    private Connection db;

    public Videos(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS videos \n"
                    + "(id SERIAL PRIMARY KEY, title TEXT, url TEXT, comment TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void add(String title, String url, String comment) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO videos (title, url, comment) VALUES (?, ?, ?)");
        stmt.setString(2, title);
        stmt.setString(1, url);
        stmt.setString(3, comment);
        stmt.executeUpdate();
        stmt.close();
    }
}
