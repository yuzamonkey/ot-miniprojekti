package ot.miniprojekti.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

public class DaoTests {

    private BookmarkDao bookmarkDao;
    private BlogDao blogDao;
    private BookDao bookDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;

    @Before
    public void setUp() {
        bookmarkDao = new BookmarkDao("test.db");
        blogDao = new BlogDao("test.db");
        bookDao = new BookDao("test.db");
        podcastDao = new PodcastDao("test.db");
        videoDao = new VideoDao("test.db");

        bookmarkDao.deleteRows();
        blogDao.deleteRows();
        bookDao.deleteRows();
        podcastDao.deleteRows();
        videoDao.deleteRows();
    }

    @Test
    public void addingBookAddsBookToDatabase() throws SQLException {
        String author = "Martin Fowler";
        String title = "Refactoring";
        String ISBN = "9780201485677";
        bookDao.add(author, title, ISBN);
        assertEquals(author, bookDao.getAll().get(0).getAuthor());
        assertEquals(title, bookDao.getAll().get(0).getTitle());
        assertEquals(ISBN, bookDao.getAll().get(0).getISBN());
    }

    @Test
    public void addingBlogAddsBlogToDatabase() throws SQLException {
        String title = "Overreacted";
        String author = "Dan Abramov";
        String url = "https://overreacted.io/";
        blogDao.add(title, author, url);
        assertEquals(title, blogDao.getAll().get(0).getTitle());
        assertEquals(author, blogDao.getAll().get(0).getAuthor());
        assertEquals(url, blogDao.getAll().get(0).getUrl());
    }

    @Test
    public void addingPodcastAddsPodcastToDatabase() throws SQLException {
        String name = "OCDevel";
        String title = "Machine Learning Guide";
        String description = "Machine learning fundamentals";
        podcastDao.add(name, title, description);
        assertEquals(name, podcastDao.getAll().get(0).getName());
        assertEquals(title, podcastDao.getAll().get(0).getTitle());
        assertEquals(description, podcastDao.getAll().get(0).getDescription());
    }

    @Test
    public void addingVideoAddsVideoToDatabase() throws SQLException {
        String title = "Java in 100 Seconds";
        String url = "https://www.youtube.com/watch?v=l9AzO1FMgM8";
        String comment = "Good explanation on Java";
        videoDao.add(title, url, comment);
        assertEquals(title, videoDao.getAll().get(0).getTitle());
        assertEquals(url, videoDao.getAll().get(0).getUrl());
        assertEquals(comment, videoDao.getAll().get(0).getComment());
    }

}
