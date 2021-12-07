package ot.miniprojekti.dao;

import java.sql.*;
import java.util.ArrayList;

public class Tags {

    private Connection db;
    private String data;

    public Tags(String data) throws SQLException {
        this.data = data;
        this.db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS tags \n"
                    + "(id SERIAL PRIMARY KEY, name TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String name) throws SQLException {
        PreparedStatement stmt = this.db.prepareStatement("INSERT INTO tags (name) VALUES (?)");
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
    }
}
