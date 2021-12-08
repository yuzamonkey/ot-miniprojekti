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

    public void addTag(List<String> tags, String bookmark) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        for (String tag : tags) {
            PreparedStatement stmt1 = this.db.prepareStatement("INSERT INTO tags (name) VALUES (?)");
            stmt1.setString(1, tag);
            stmt1.executeUpdate();
            PreparedStatement stmt2 = this.db.prepareStatement("SELECT last_insert_rowid() AS tag_id");
            ResultSet result = stmt2.executeQuery();
            int tagId = Integer.parseInt(result.getString("tag_id"));
            stmt1.close();
            stmt2.close();
            addBookmarkConnection(bookmark, tagId);
        }
    }

    public int tagIdByName(String name) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt = db.prepareStatement("SELECT id FROM tags WHERE name='" + name + "' LIMIT 1");
        ResultSet result = stmt.executeQuery();
        int id = result.getInt("id");
        db.close();
        return id;
    }

    public void addBookmarkConnection(String bookmark, int tagId) throws SQLException {
        if (bookmark.equals("blogs")) return;
        db = DriverManager.getConnection("jdbc:sqlite:" + bookmark + ".db");
        PreparedStatement stmt1 = this.db.prepareStatement("SELECT id FROM " + bookmark + " ORDER BY id DESC LIMIT 1");
        ResultSet result = stmt1.executeQuery();
        int bookmarkId = Integer.parseInt(result.getString("id"));
        stmt1.close();

        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        PreparedStatement stmt2 = db.prepareStatement("INSERT INTO tagconnections (tag_id, "
                + bookmark.substring(0, bookmark.length() - 1) + "_id) VALUES (?, ?)");
        stmt2.setInt(1, tagId);
        stmt2.setInt(2, bookmarkId);
        stmt2.executeUpdate();
        stmt2.close();
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
