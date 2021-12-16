package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.domain.Podcast;

public class PodcastDao {

    private Connection conn;
    private String db;

    public PodcastDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS podcast "
                    + "(id INTEGER PRIMARY KEY, bookmark_id INTEGER, name TEXT, title TEXT, description TEXT, "
                    + "FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void add(String name, String title, String description) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookmark DEFAULT VALUES");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT last_insert_rowid() AS bookmark_id");
            ResultSet r = stmt.executeQuery();
            stmt = conn.prepareStatement(
                    "INSERT INTO podcast (bookmark_id, name, title, description) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(r.getString("bookmark_id")));
            stmt.setString(2, name);
            stmt.setString(3, title);
            stmt.setString(4, description);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Podcast> getUnread() {
        ArrayList<Podcast> podcasts = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM podcast p, bookmark b WHERE p.bookmark_id = b.id "
                    + "AND b.read = 0");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("bookmark_id");
                String name = r.getString("name");
                String title = r.getString("title");
                String description = r.getString("description");
                podcasts.add(new Podcast(id, title, name, description, false));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return podcasts;
    }
    
    public ArrayList<Podcast> getRead() {
        ArrayList<Podcast> podcasts = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT bm.id, p.name, p.title, p.description, bm.comment "
                    + "FROM bookmark bm JOIN podcast p ON bm.id = p.bookmark_id WHERE bm.read = 1");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String title = r.getString("title");
                String description = r.getString("description");
                String note = r.getString("comment");
                
                Podcast p = new Podcast(id, title, name, description, true);
                p.setNote(note);
                podcasts.add(p);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return podcasts;
    }

    public ArrayList<Podcast> findByTag(String tag) {
        ArrayList<Podcast> podcasts = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT p.id, p.name, p.title, p.description FROM bookmark bm "
                    + "JOIN tag t ON bm.id = t.bookmark_id JOIN podcast p ON bm.id = p.bookmark_id "
                    + "WHERE t.name LIKE '" + tag + "'");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String title = r.getString("title");
                String description = r.getString("description");
                podcasts.add(new Podcast(id, title, name, description, false));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return podcasts;
    }

    public void deleteRows() {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM podcast");
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
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM podcast WHERE bookmark_id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            boolean deleted = r.next();
            
            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM podcast WHERE bookmark_id='" + id + "'");
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
