package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookmarkDao {

    private Connection conn;
    private String db;

    public BookmarkDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            Statement s = conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS bookmark "
                    + "(id INTEGER PRIMARY KEY, visible INTEGER, read INTEGER, comment TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS tag (id INTEGER PRIMARY KEY, "
                    + "bookmark_id INTEGER, name TEXT, FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTag(List<String> tags) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT id AS bookmark_id FROM bookmark "
                    + "ORDER BY id DESC LIMIT 1");
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

    public void deleteRows() {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM bookmark");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
