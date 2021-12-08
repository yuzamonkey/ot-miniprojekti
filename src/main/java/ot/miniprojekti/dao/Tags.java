package ot.miniprojekti.dao;

import java.sql.*;
import java.util.List;

public class Tags {

    private Connection db;
    private String data;

    public Tags(String data) throws SQLException {
        this.data = data;
        this.db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS tags \n"
                    + "(id INTEGER PRIMARY KEY, name TEXT)");
            stmt.executeUpdate();

            stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS tagconnections \n"
                    + "(id INTEGER PRIMARY KEY,"
                    + "tag_id INTEGER REFERENCES tags DEFAULT NULL,"
                    + "book_id INTEGER REFERENCES books DEFAULT NULL,"
                    + "video_id INTEGER REFERENCES videos DEFAULT NULL,"
                    + "blog_id INTEGER REFERENCES blogposts DEFAULT NULL,"
                    + "podcast_id INTEGER REFERENCES podcasts DEFAULT NULL)");

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addTag(List<String> tags) throws SQLException {
        PreparedStatement stmt = this.db.prepareStatement("INSERT INTO tags (name) VALUES (?)");
        for (String tag : tags) {
            stmt.setString(1, tag);
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
    }

    public int tagIdByName(String name) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("SELECT id FROM tags WHERE name='" + name + "' LIMIT 1");
        ResultSet result = stmt.executeQuery();
        int id = result.getInt("id");
        db.close();
        return id;
    }

    public void addBookConnection(String bookmark, int bookmarkId, int tagId) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO tagconnections (tag_id, "
                + bookmark + "_id) VALUES (?, ?)");
        stmt.setInt(1, tagId);
        stmt.setInt(2, bookmarkId);
        stmt.executeUpdate();
        stmt.close();
    }

    // public void findBookByTagName(String name) throws SQLException {
    // int tagId = tagIdByName(name);

    // db = DriverManager.getConnection("jdbc:sqlite:" + data);
    // ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

    // PreparedStatement stmt = db.prepareStatement("SELECT * FROM tagconnections
    // WHERE book_id = " + tagId);
    // ResultSet result = stmt.executeQuery();

    // while (result.next()) {
    // String author = result.getString("author");
    // String title = result.getString("title");
    // String isbn = result.getString("isbn");
    // books.add(new Book(author, title, isbn));
    // }

    // db.close();

    // return books;
    // }
}
