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
                    "(id INTEGER PRIMARY KEY, author TEXT, name TEXT, type TEXT, isbn TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void add(String author, String name, String isbn) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO books (author, name, type, isbn) VALUES (?, ?, 'book', ?)");
        stmt.setString(1, author);
        stmt.setString(2, name);
        stmt.setString(4, isbn);
        stmt.executeUpdate();
        stmt.close();
    }
    
    
}
