package ot.miniprojekti;

import java.sql.SQLException;
import java.util.Scanner;
import ot.miniprojekti.dao.Blogs;
import ot.miniprojekti.dao.Books;
import ot.miniprojekti.dao.Podcasts;
import ot.miniprojekti.dao.Tags;
import ot.miniprojekti.dao.Videos;
import ot.miniprojekti.logic.BookmarkManager;
import ot.miniprojekti.ui.TextUserInterface;

public class App {

    public static void main(String[] args) throws SQLException {
        Scanner reader = new Scanner(System.in);

        Books books = new Books("books.db");
        Videos videos = new Videos("videos.db");
        Blogs blogs = new Blogs("blogs.db");
        Podcasts podcasts = new Podcasts("podcasts.db");
        Tags tags = new Tags("tags.db");
        BookmarkManager bookmarkManager = new BookmarkManager(books, videos, blogs, podcasts, tags);

        TextUserInterface textUserInterface = new TextUserInterface(bookmarkManager, reader);
        textUserInterface.start();
    }
}
