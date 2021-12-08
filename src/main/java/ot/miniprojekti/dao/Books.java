package ot.miniprojekti.dao;

import ot.miniprojekti.domain.Book;

import java.sql.*;
import java.util.ArrayList;

public class Books {

    private Connection db;
    private String data;

    public Books(String data) throws SQLException {
        this.data = data;
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS books \n"
                    + "(id INTEGER PRIMARY KEY, author TEXT, title TEXT, isbn TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String author, String title, String isbn) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO books (author, title, isbn) VALUES (?, ?, ?)");
        stmt.setString(1, author);
        stmt.setString(2, title);
        stmt.setString(3, isbn);
        stmt.executeUpdate();
        stmt.close();
    }
    
    public ArrayList<Book> getAll() throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        ArrayList<Book> books = new ArrayList<Book>();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM books");
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            String author = result.getString("author");
            String title = result.getString("title");
            String isbn = result.getString("isbn");
            books.add(new Book(author, title, isbn));
        }
        db.close();

        return books;
    }
}
