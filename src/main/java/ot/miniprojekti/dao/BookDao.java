package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.domain.Book;

public class BookDao {

    private Connection conn;
    private String db;

    public BookDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS book "
                    + "(id INTEGER PRIMARY KEY, bookmark_id INTEGER, author TEXT, title TEXT, isbn TEXT, "
                    + "FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void add(String author, String title, String isbn) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookmark DEFAULT VALUES");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT last_insert_rowid() AS bookmark_id");
            ResultSet r = stmt.executeQuery();
            stmt = conn.prepareStatement("INSERT INTO book (bookmark_id, author, title, isbn) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(r.getString("bookmark_id")));
            stmt.setString(2, author);
            stmt.setString(3, title);
            stmt.setString(4, isbn);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("bookmark_id");
                String author = r.getString("author");
                String title = r.getString("title");
                String isbn = r.getString("isbn");
                books.add(new Book(id, author, title, isbn));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return books;
    }

    public ArrayList<Book> findByTag(String tag) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT b.id, b.author, b.title, b.isbn FROM bookmark bm "
                    + "JOIN tag t ON bm.id = t.bookmark_id JOIN book b ON bm.id = b.bookmark_id "
                    + "WHERE t.name LIKE '" + tag + "'");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String author = r.getString("author");
                String title = r.getString("title");
                String isbn = r.getString("isbn");
                books.add(new Book(id, author, title, isbn));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return books;
    }

    public void deleteRows() {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM book");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean deleteByBookmarkId(int id) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM book WHERE bookmark_id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            
            boolean deleted = r.next();
            
            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM book WHERE bookmark_id='" + id + "'");
                stmt.executeUpdate();
            }
            stmt.close();
            conn.close();
            return deleted;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
