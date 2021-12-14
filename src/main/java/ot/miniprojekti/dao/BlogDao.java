package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.domain.Blog;

public class BlogDao {

    private Connection conn;
    private String db;

    public BlogDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS blog "
                    + "(id INTEGER PRIMARY KEY, bookmark_id INTEGER, title TEXT, author TEXT, url TEXT, "
                    + "FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void add(String title, String author, String url) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookmark DEFAULT VALUES");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT last_insert_rowid() AS bookmark_id");
            ResultSet r = stmt.executeQuery();
            stmt = conn.prepareStatement("INSERT INTO blog (bookmark_id, title, author, url) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(r.getString("bookmark_id")));
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, url);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Blog> getAll() {
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM blog");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("bookmark_id");
                String title = r.getString("title");
                String author = r.getString("author");
                String url = r.getString("url");
                blogs.add(new Blog(id, title, author, url));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return blogs;
    }

    public ArrayList<Blog> findByTag(String tag) {
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT b.id, b.title, b.author, b.url FROM bookmark bm "
                    + "JOIN tag t ON bm.id = t.bookmark_id JOIN blog b ON bm.id = b.bookmark_id "
                    + "WHERE t.name LIKE '" + tag + "'");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String title = r.getString("title");
                String author = r.getString("author");
                String url = r.getString("url");
                blogs.add(new Blog(id, title, author, url));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return blogs;
    }

    public void deleteRows() {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM blog");
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
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM blog WHERE bookmark_id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            boolean deleted = r.next();
            
            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM blog WHERE bookmark_id='" + id + "'");
                stmt.executeUpdate();
            }

            stmt.close();
            conn.close();
            return deleted;
        } catch (SQLException e) {
            return false;
        }
    }
}
