package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ot.miniprojekti.domain.Book;

public class BookmarkDao {

    private Connection conn;
    private String db;

    public BookmarkDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            Statement s = conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS bookmark (id INTEGER PRIMARY KEY, visible INTEGER, read INTEGER, comment TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS tag (id INTEGER PRIMARY KEY, bookmark_id INTEGER, name TEXT, FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTag(List<String> tags) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT id AS bookmark_id FROM bookmark ORDER BY id DESC LIMIT 1");
            ResultSet r = stmt.executeQuery();
            int bookmarkId = Integer.parseInt(r.getString("bookmark_id"));
            stmt = conn.prepareStatement("INSERT INTO tag (bookmark_id, name) VALUES (?, ?)");

            for (String tag : tags) {
                stmt.setInt(1, bookmarkId);
                stmt.setString(2, tag);
                stmt.addBatch();
            }

            stmt.executeBatch();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Book> findBooksByTag(String tag) {
        ArrayList<Book> books = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT b.author, b.title, b.isbn FROM bookmark bm "
                    + "JOIN tag t ON bm.id = t.bookmark_id JOIN book b ON bm.id = b.bookmark_id WHERE t.name LIKE '" + tag + "'");
            ResultSet r = stmt.executeQuery();
            
            while (r.next()) {
                String author = r.getString("author");
                String title = r.getString("title");
                String isbn = r.getString("isbn");
                System.out.println(author + title + isbn);
                books.add(new Book(author, title, isbn));
            }
            
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return books;
    }
}
