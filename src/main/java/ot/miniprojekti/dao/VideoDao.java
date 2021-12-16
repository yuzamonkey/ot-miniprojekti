package ot.miniprojekti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.domain.Video;

public class VideoDao {

    private Connection conn;
    private String db;

    public VideoDao(String db) {
        this.db = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(this.db);
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS video "
                    + "(id INTEGER PRIMARY KEY, bookmark_id INTEGER, title TEXT, url TEXT, comment TEXT, "
                    + "FOREIGN KEY(bookmark_id) REFERENCES bookmark(id))");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void add(String title, String url, String comment) {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookmark DEFAULT VALUES");
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT last_insert_rowid() AS bookmark_id");
            ResultSet r = stmt.executeQuery();
            stmt = conn.prepareStatement("INSERT INTO video (bookmark_id, title, url, comment) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(r.getString("bookmark_id")));
            stmt.setString(2, title);
            stmt.setString(3, url);
            stmt.setString(4, comment);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Video> getUnread() {
        ArrayList<Video> videos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM video v, bookmark b WHERE v.bookmark_id = b.id "
                    + "AND b.read = 0");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("bookmark_id");
                String title = r.getString("title");
                String url = r.getString("url");
                String comment = r.getString("comment");
                videos.add(new Video(id, title, url, comment, false));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return videos;
    }
    
    public ArrayList<Video> getRead() {
        ArrayList<Video> videos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT bm.id, v.title, v.url, v.comment, bm.comment as note "
                    + "FROM bookmark bm JOIN video v ON bm.id = v.bookmark_id WHERE bm.read = 1");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String title = r.getString("title");
                String url = r.getString("url");
                String comment = r.getString("comment");
                String note = r.getString("note");
                
                Video v = new Video(id, title, url, comment, true);
                v.setNote(note);
                videos.add(v);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return videos;
    }

    public ArrayList<Video> findByTag(String tag) {
        ArrayList<Video> videos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("SELECT v.id, v.title, v.url, v.comment FROM bookmark bm "
                    + "JOIN tag t ON bm.id = t.bookmark_id JOIN video v ON bm.id = v.bookmark_id "
                    + "WHERE t.name LIKE '" + tag + "'");
            ResultSet r = stmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("id");
                String title = r.getString("title");
                String url = r.getString("url");
                String comment = r.getString("comment");
                videos.add(new Video(id, title, url, comment, false));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return videos;
    }

    public void deleteRows() {
        try {
            conn = DriverManager.getConnection(db);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM video");
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
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM video WHERE bookmark_id='" + id + "'");
            ResultSet r = stmt.executeQuery();
            boolean deleted = r.next();
            
            if (deleted) {
                stmt = conn.prepareStatement("DELETE FROM video WHERE bookmark_id='" + id + "'");
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
