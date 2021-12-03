package ot.miniprojekti.dao;

import java.sql.*;
import java.util.ArrayList;
import ot.miniprojekti.domain.Bookmark;

public class Books {
    
    private Connection db;
    
    public Books(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS books \n" + 
                    "(id INTEGER PRIMARY KEY, author TEXT, title TEXT, isbn TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void add(String author, String title, String isbn) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO books (author, title, isbn) VALUES (?, ?, ?)");
        stmt.setString(1, author);
        stmt.setString(2, title);
        stmt.setString(3, isbn);
        stmt.executeUpdate();
        stmt.close();
    }
    
    
}
