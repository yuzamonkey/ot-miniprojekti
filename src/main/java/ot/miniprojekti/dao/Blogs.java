package ot.miniprojekti.dao;

import ot.miniprojekti.domain.Blog;

import java.sql.*;
import java.util.ArrayList;

public class Blogs {

    private Connection db;

    public Blogs(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS blogposts \n"
                    + "(id SERIAL PRIMARY KEY, title TEXT, author TEXT, url TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String title, String author, String url) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO blogposts (title, author, url) VALUES (?, ?, ?)");
        stmt.setString(2, title);
        stmt.setString(1, author);
        stmt.setString(3, url);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Blog> getAll() throws SQLException {
        ArrayList<Blog> blogs = new ArrayList<Blog>();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM blogposts");
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String author = result.getString("author");
            String url = result.getString("url");
            blogs.add(new Blog(id, title, author, url));
        }
        db.close();

        return blogs;
    }
}
