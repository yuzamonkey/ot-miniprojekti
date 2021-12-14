package ot.miniprojekti.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ot.miniprojekti.domain.*;

import java.sql.SQLException;
import java.util.*;

public class DaoTests {

    private BookmarkDao bookmarkDao;
    private BookDao bookDao;
    private BlogDao blogDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;

    private Book book;
    private Blog blog;
    private Podcast podcast;
    private Video video;

    @Before
    public void setUp() {
        bookmarkDao = new BookmarkDao("test.db");
        bookDao = new BookDao("test.db");
        blogDao = new BlogDao("test.db");
        podcastDao = new PodcastDao("test.db");
        videoDao = new VideoDao("test.db");

        bookmarkDao.deleteRows();
        bookDao.deleteRows();
        blogDao.deleteRows();
        podcastDao.deleteRows();
        videoDao.deleteRows();

        book = new Book(0, "Martin Fowler", "Refactoring", "9780201485677");
        blog = new Blog(0, "Overreacted", "Dan Abramov", "https://overreacted.io/");
        podcast = new Podcast(0, "OCDevel", "Machine Learning Guide", "Machine learning fundamentals");
        video = new Video(0, "Java in 100 Seconds", "https://www.youtube.com/watch?v=l9AzO1FMgM8",
                "Good explanation on Java");
    }

    @Test
    public void dbIsEmptyWhenNothingIsAdded() {
        assertEquals(bookDao.getAll().size(), 0);
        assertEquals(blogDao.getAll().size(), 0);
        assertEquals(podcastDao.getAll().size(), 0);
        assertEquals(videoDao.getAll().size(), 0);
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
    public void findByTagFindsCorrectBook() throws SQLException {
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

    @Test
    public void findByTagFindsCorrectBlog() throws SQLException {
        blogDao.add(blog.getTitle(), blog.getAuthor(), blog.getUrl());
        List<String> tags = new ArrayList<String>();
        tags.add("react");
        tags.add("javascript");
        bookmarkDao.addTag(tags);
        ArrayList<Blog> blogs1 = blogDao.findByTag("react");
        ArrayList<Blog> blogs2 = blogDao.findByTag("javascript");
        ArrayList<Blog> blogs3 = blogDao.findByTag("programming");
        ArrayList<Blog> blogs4 = blogDao.findByTag("agile");
        assertEquals(blog.getAuthor(), blogs1.get(0).getAuthor());
        assertEquals(blog.getTitle(), blogs1.get(0).getTitle());
        assertEquals(blog.getUrl(), blogs1.get(0).getUrl());
        assertEquals(blogs1.get(0).getAuthor(), blogs2.get(0).getAuthor());
        assertEquals(blogs1.get(0).getTitle(), blogs2.get(0).getTitle());
        assertEquals(blogs1.get(0).getUrl(), blogs2.get(0).getUrl());
        assertEquals(blogs3.size(), 0);
        assertEquals(blogs4.size(), 0);
    }

    @Test
    public void findByTagFindsCorrectPodcast() throws SQLException {
        podcastDao.add(podcast.getName(), podcast.getTitle(), podcast.getDescription());
        List<String> tags = new ArrayList<String>();
        tags.add("ml");
        tags.add("ai");
        bookmarkDao.addTag(tags);
        ArrayList<Podcast> podcasts1 = podcastDao.findByTag("ml");
        ArrayList<Podcast> podcasts2 = podcastDao.findByTag("ai");
        ArrayList<Podcast> podcasts3 = podcastDao.findByTag("programming");
        ArrayList<Podcast> podcasts4 = podcastDao.findByTag("agile");
        assertEquals(podcast.getName(), podcasts1.get(0).getName());
        assertEquals(podcast.getTitle(), podcasts1.get(0).getTitle());
        assertEquals(podcast.getDescription(), podcasts1.get(0).getDescription());
        assertEquals(podcasts1.get(0).getName(), podcasts2.get(0).getName());
        assertEquals(podcasts1.get(0).getTitle(), podcasts2.get(0).getTitle());
        assertEquals(podcasts1.get(0).getDescription(), podcasts2.get(0).getDescription());
        assertEquals(podcasts3.size(), 0);
        assertEquals(podcasts4.size(), 0);
    }

    @Test
    public void findByTagFindsCorrectVideo() throws SQLException {
        videoDao.add(video.getTitle(), video.getUrl(), video.getComment());
        List<String> tags = new ArrayList<String>();
        tags.add("ml");
        tags.add("ai");
        bookmarkDao.addTag(tags);
        ArrayList<Video> videos1 = videoDao.findByTag("ml");
        ArrayList<Video> videos2 = videoDao.findByTag("ai");
        ArrayList<Video> videos3 = videoDao.findByTag("programming");
        ArrayList<Video> videos4 = videoDao.findByTag("agile");
        assertEquals(video.getTitle(), videos1.get(0).getTitle());
        assertEquals(video.getUrl(), videos1.get(0).getUrl());
        assertEquals(video.getComment(), videos1.get(0).getComment());
        assertEquals(videos1.get(0).getUrl(), videos2.get(0).getUrl());
        assertEquals(videos1.get(0).getTitle(), videos2.get(0).getTitle());
        assertEquals(videos1.get(0).getComment(), videos2.get(0).getComment());
        assertEquals(videos3.size(), 0);
        assertEquals(videos4.size(), 0);
    }
}
