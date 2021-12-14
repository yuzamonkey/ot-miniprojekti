package ot.miniprojekti.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ot.miniprojekti.domain.*;
import ot.miniprojekti.dao.*;

import java.util.ArrayList;

public class LogicTests {

    private BookmarkDao bookmarkDao;
    private BookDao bookDao;
    private VideoDao videoDao;
    private BlogDao blogDao;
    private PodcastDao podcastDao;

    private BookmarkManager bookmarkManager;

    Book book1;
    Book book2;
    Blog blog1;
    Blog blog2;
    Podcast podcast1;
    Podcast podcast2;
    Video video1;
    Video video2;

    @Before
    public void setUp() {
        bookmarkDao = new BookmarkDao("test.db");
        bookDao = new BookDao("test.db");
        videoDao = new VideoDao("test.db");
        blogDao = new BlogDao("test.db");
        podcastDao = new PodcastDao("test.db");

        bookmarkDao.deleteRows();
        bookDao.deleteRows();
        blogDao.deleteRows();
        podcastDao.deleteRows();
        videoDao.deleteRows();

        bookmarkManager = new BookmarkManager(bookmarkDao, bookDao, videoDao, blogDao, podcastDao);

        book1 = new Book(0, "Martin Fowler", "Refactoring", "9780201485677");
        book2 = new Book(0, "JK Rowling", "Harry Potter and the Philosopher's Stone", "9780747532743");
        blog1 = new Blog(0, "Overreacted", "Dan Abramov", "https://overreacted.io/");
        blog2 = new Blog(4, "Bloggier", "John Doe", "https://johndoesblog.com/");
        podcast1 = new Podcast(0, "OCDevel", "Machine Learning Guide", "Machine learning fundamentals");
        podcast2 = new Podcast(1, "MKBHD", "Waveform", "Tech stuff");
        video1 = new Video(0, "Java in 100 Seconds", "https://www.youtube.com/watch?v=l9AzO1FMgM8",
                "Good explanation on Java");
        video2 = new Video(1, "Crypto expert explain the Blockchain",
                "https://www.youtube.com/watch?v=pSTNhBlfV_s&t=3s",
                "Good explanation on crypto");

    }

    @Test
    public void dbIsEmptyWhenNothingIsAdded() {
        assertEquals(bookmarkManager.getBooks().size(), 0);
        assertEquals(bookmarkManager.getBlogs().size(), 0);
        assertEquals(bookmarkManager.getPodcasts().size(), 0);
        assertEquals(bookmarkManager.getVideos().size(), 0);
    }

    @Test
    public void addBookAddsBook() {
        bookmarkManager.addBook(book1.getAuthor(), book1.getTitle(), book1.getISBN());
        bookmarkManager.addBook(book2.getAuthor(), book2.getTitle(), book2.getISBN());

        ArrayList<Book> books = bookmarkManager.getBooks();
        assertEquals(books.size(), 2);

        assertEquals(books.get(0).getAuthor(), book1.getAuthor());
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(0).getISBN(), book1.getISBN());

        assertEquals(books.get(1).getAuthor(), book2.getAuthor());
        assertEquals(books.get(1).getTitle(), book2.getTitle());
        assertEquals(books.get(1).getISBN(), book2.getISBN());
    }

    @Test
    public void addBlogAddsBlog() {
        bookmarkManager.addBlog(blog1.getTitle(), blog1.getAuthor(), blog1.getUrl());
        bookmarkManager.addBlog(blog2.getTitle(), blog2.getAuthor(), blog2.getUrl());

        ArrayList<Blog> blogs = bookmarkManager.getBlogs();
        assertEquals(blogs.size(), 2);

        assertEquals(blogs.get(0).getAuthor(), blog1.getAuthor());
        assertEquals(blogs.get(0).getTitle(), blog1.getTitle());
        assertEquals(blogs.get(0).getUrl(), blog1.getUrl());

        assertEquals(blogs.get(1).getAuthor(), blog2.getAuthor());
        assertEquals(blogs.get(1).getTitle(), blog2.getTitle());
        assertEquals(blogs.get(1).getUrl(), blog2.getUrl());
    }

    @Test
    public void addPodcastAddsPodcast() {
        bookmarkManager.addPodcast(podcast1.getName(), podcast1.getTitle(), podcast1.getDescription());
        bookmarkManager.addPodcast(podcast2.getName(), podcast2.getTitle(), podcast2.getDescription());

        ArrayList<Podcast> podcasts = bookmarkManager.getPodcasts();
        assertEquals(podcasts.size(), 2);

        assertEquals(podcasts.get(0).getName(), podcast1.getName());
        assertEquals(podcasts.get(0).getTitle(), podcast1.getTitle());
        assertEquals(podcasts.get(0).getDescription(), podcast1.getDescription());

        assertEquals(podcasts.get(1).getName(), podcast2.getName());
        assertEquals(podcasts.get(1).getTitle(), podcast2.getTitle());
        assertEquals(podcasts.get(1).getDescription(), podcast2.getDescription());
    }

    @Test
    public void addVideoAddsVideo() {
        bookmarkManager.addVideo(video1.getTitle(), video1.getUrl(), video1.getComment());
        bookmarkManager.addVideo(video2.getTitle(), video2.getUrl(), video2.getComment());

        ArrayList<Video> videos = bookmarkManager.getVideos();
        assertEquals(videos.size(), 2);

        assertEquals(videos.get(0).getTitle(), video1.getTitle());
        assertEquals(videos.get(0).getUrl(), video1.getUrl());
        assertEquals(videos.get(0).getComment(), video1.getComment());

        assertEquals(videos.get(1).getTitle(), video2.getTitle());
        assertEquals(videos.get(1).getUrl(), video2.getUrl());
        assertEquals(videos.get(1).getComment(), video2.getComment());
    }

}
