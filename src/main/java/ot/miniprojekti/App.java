package ot.miniprojekti;

import java.sql.SQLException;
import java.util.Scanner;
import ot.miniprojekti.dao.BlogDao;
import ot.miniprojekti.dao.BookDao;
import ot.miniprojekti.dao.BookmarkDao;
import ot.miniprojekti.dao.PodcastDao;
import ot.miniprojekti.dao.VideoDao;
import ot.miniprojekti.logic.BookmarkManager;
import ot.miniprojekti.ui.TextUserInterface;

public class App {

    public static void main(String[] args) throws SQLException {
        Scanner reader = new Scanner(System.in);

        BookmarkDao bookmarkDao = new BookmarkDao("bookmark.db");
        BookDao bookDao = new BookDao("bookmark.db");
        VideoDao videoDao = new VideoDao("bookmark.db");
        BlogDao blogDao = new BlogDao("bookmark.db");
        PodcastDao podcastDao = new PodcastDao("bookmark.db");

        BookmarkManager bookmarkManager = new BookmarkManager(bookmarkDao, bookDao, videoDao, blogDao, podcastDao);

        TextUserInterface textUserInterface = new TextUserInterface(bookmarkManager, reader);
        textUserInterface.start();
    }
}
