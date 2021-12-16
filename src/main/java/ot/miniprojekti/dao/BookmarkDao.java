package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                    + "(id INTEGER PRIMARY KEY, read INTEGER DEFAULT 0, comment TEXT)");
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
            Statement s = conn.createStatement();
            s.execute("DELETE FROM bookmark");
            s.execute("DELETE FROM tag");
            s.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean deleteBookmarkById(int id) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM bookmark WHERE id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            boolean deleted = r.next();

            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM bookmark WHERE id='" + id + "'");
                stmt.executeUpdate();
            }

            stmt.close();
            conn.close();
            return deleted;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteTagByBookmarkId(int id) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM tag WHERE bookmark_id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            boolean deleted = r.next();

            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM tag WHERE bookmark_id='" + id + "'");
                stmt.executeUpdate();
            }

            stmt.close();
            conn.close();
            return deleted;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean isBookmark(int id) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bookmark");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int bmId = r.getInt("id");
                ids.add(bmId);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return ids.contains(id);
    }

    public boolean markAsRead(int id, String comment) {
        try {
            conn = DriverManager.getConnection(db);
            Statement s = conn.createStatement();
            s.execute("UPDATE bookmark SET read = 1 WHERE id ='" + id + "'");
            if (!"".equals(comment)) {
                s.execute("UPDATE bookmark SET comment = '" + comment + "' WHERE id = '" + id + "'");
            }
            s.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
