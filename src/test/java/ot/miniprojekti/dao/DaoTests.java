package ot.miniprojekti.dao;

import org.junit.Before;
import org.junit.Test;

import ot.miniprojekti.domain.*;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

public class DaoTests {

    private BookmarkDao bookmarkDao;
    private BlogDao blogDao;
    private BookDao bookDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;

    private Book book;
    private Blog blog;
    private Podcast podcast;
    private Video video;

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

        book = new Book("Martin Fowler", "Refactoring", "9780201485677");
        blog = new Blog(0, "Overreacted", "Dan Abramov", "https://overreacted.io/");
        podcast = new Podcast("OCDevel", "Machine Learning Guide", "Machine learning fundamentals");
        video = new Video(0, "Java in 100 Seconds", "https://www.youtube.com/watch?v=l9AzO1FMgM8",
                "Good explanation on Java");
    }

    @Test
    public void addingBookAddsBookToDatabase() throws SQLException {
        bookDao.add(book.getAuthor(), book.getTitle(), book.getISBN());
        assertEquals(book.getAuthor(), bookDao.getAll().get(0).getAuthor());
        assertEquals(book.getTitle(), bookDao.getAll().get(0).getTitle());
        assertEquals(book.getISBN(), bookDao.getAll().get(0).getISBN());
    }

    @Test
    public void addingBlogAddsBlogToDatabase() throws SQLException {
        blogDao.add(blog.getTitle(), blog.getAuthor(), blog.getUrl());
        assertEquals(blog.getTitle(), blogDao.getAll().get(0).getTitle());
        assertEquals(blog.getAuthor(), blogDao.getAll().get(0).getAuthor());
        assertEquals(blog.getUrl(), blogDao.getAll().get(0).getUrl());
    }

    @Test
    public void addingPodcastAddsPodcastToDatabase() throws SQLException {
        podcastDao.add(podcast.getName(), podcast.getTitle(), podcast.getDescription());
        assertEquals(podcast.getName(), podcastDao.getAll().get(0).getName());
        assertEquals(podcast.getTitle(), podcastDao.getAll().get(0).getTitle());
        assertEquals(podcast.getDescription(), podcastDao.getAll().get(0).getDescription());
    }

    @Test
    public void addingVideoAddsVideoToDatabase() throws SQLException {
        videoDao.add(video.getTitle(), video.getUrl(), video.getComment());
        assertEquals(video.getTitle(), videoDao.getAll().get(0).getTitle());
        assertEquals(video.getUrl(), videoDao.getAll().get(0).getUrl());
        assertEquals(video.getComment(), videoDao.getAll().get(0).getComment());
    }

    @Test
    public void findByTagFindsCorrectBookmarks() throws SQLException {
        bookDao.add(book.getAuthor(), book.getTitle(), book.getISBN());
        List<String> tags = new ArrayList<String>();
        tags.add("fowler");
        tags.add("agile");
        bookmarkDao.addTag(tags);
        ArrayList<Book> books1 = bookDao.findByTag("fowler");
        ArrayList<Book> books2 = bookDao.findByTag("agile");
        ArrayList<Book> books3 = bookDao.findByTag("programming");
        assertEquals(book.getAuthor(), books1.get(0).getAuthor());
        assertEquals(book.getTitle(), books1.get(0).getTitle());
        assertEquals(book.getISBN(), books1.get(0).getISBN());
        assertEquals(books1.get(0).getAuthor(), books2.get(0).getAuthor());
        assertEquals(books1.get(0).getTitle(), books2.get(0).getTitle());
        assertEquals(books1.get(0).getISBN(), books2.get(0).getISBN());
        assertEquals(books3.size(), 0);
    }
}
