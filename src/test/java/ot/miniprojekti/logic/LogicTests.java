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

    private Book book1;
    private Book book2;
    private Blog blog1;
    private Blog blog2;
    private Podcast podcast1;
    private Podcast podcast2;
    private Video video1;
    private Video video2;

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

    @Test
    public void addingTagsReturnsCorrectBookmarks() {
        bookmarkManager.addBook(book1.getAuthor(), book1.getTitle(), book1.getISBN());
        bookmarkManager.addTag("fowler, agile, book");
        bookmarkManager.addBook(book2.getAuthor(), book2.getTitle(), book2.getISBN());
        bookmarkManager.addTag("potter, rowling, book");

        bookmarkManager.addBlog(blog1.getTitle(), blog1.getAuthor(), blog1.getUrl());
        bookmarkManager.addTag("react, javascript, blog");
        bookmarkManager.addBlog(blog2.getTitle(), blog2.getAuthor(), blog2.getUrl());
        bookmarkManager.addTag("john, doe, blog");

        bookmarkManager.addPodcast(podcast1.getName(), podcast1.getTitle(), podcast1.getDescription());
        bookmarkManager.addTag("ml, ai, podcast");
        bookmarkManager.addPodcast(podcast2.getName(), podcast2.getTitle(), podcast2.getDescription());
        bookmarkManager.addTag("tech, podcast");

        bookmarkManager.addVideo(video1.getTitle(), video1.getUrl(), video1.getComment());
        bookmarkManager.addTag("java, programming, video");
        bookmarkManager.addVideo(video2.getTitle(), video2.getUrl(), video2.getComment());
        bookmarkManager.addTag("crypto, decentralization, video");

        ArrayList<Book> books1 = bookmarkManager.getBooksByTagName("fowler");
        ArrayList<Book> books2 = bookmarkManager.getBooksByTagName("potter");
        ArrayList<Book> books3 = bookmarkManager.getBooksByTagName("book");
        ArrayList<Book> books4 = bookmarkManager.getBooksByTagName("java");

        assertEquals(books1.size(), 1);
        assertEquals(books2.size(), 1);
        assertEquals(books3.size(), 2);
        assertEquals(books4.size(), 0);

        assertEquals(books1.get(0).getTitle(), book1.getTitle());
        assertEquals(books2.get(0).getTitle(), book2.getTitle());
        assertEquals(books3.get(0).getTitle(), book1.getTitle());
        assertEquals(books3.get(1).getTitle(), book2.getTitle());

        ArrayList<Blog> blogs1 = bookmarkManager.getBlogsByTagName("react");
        ArrayList<Blog> blogs2 = bookmarkManager.getBlogsByTagName("doe");
        ArrayList<Blog> blogs3 = bookmarkManager.getBlogsByTagName("blog");
        ArrayList<Blog> blogs4 = bookmarkManager.getBlogsByTagName("agile");

        assertEquals(blogs1.size(), 1);
        assertEquals(blogs2.size(), 1);
        assertEquals(blogs3.size(), 2);
        assertEquals(blogs4.size(), 0);

        assertEquals(blogs1.get(0).getTitle(), blog1.getTitle());
        assertEquals(blogs2.get(0).getTitle(), blog2.getTitle());
        assertEquals(blogs3.get(0).getTitle(), blog1.getTitle());
        assertEquals(blogs3.get(1).getTitle(), blog2.getTitle());

        ArrayList<Podcast> podcasts1 = bookmarkManager.getPodcastsByTagName("ml");
        ArrayList<Podcast> podcasts2 = bookmarkManager.getPodcastsByTagName("tech");
        ArrayList<Podcast> podcasts3 = bookmarkManager.getPodcastsByTagName("podcast");
        ArrayList<Podcast> podcasts4 = bookmarkManager.getPodcastsByTagName("agile");

        assertEquals(podcasts1.size(), 1);
        assertEquals(podcasts2.size(), 1);
        assertEquals(podcasts3.size(), 2);
        assertEquals(podcasts4.size(), 0);

        assertEquals(podcasts1.get(0).getTitle(), podcast1.getTitle());
        assertEquals(podcasts2.get(0).getTitle(), podcast2.getTitle());
        assertEquals(podcasts3.get(0).getTitle(), podcast1.getTitle());
        assertEquals(podcasts3.get(1).getTitle(), podcast2.getTitle());

        ArrayList<Video> videos1 = bookmarkManager.getVideosByTagName("java");
        ArrayList<Video> videos2 = bookmarkManager.getVideosByTagName("crypto");
        ArrayList<Video> videos3 = bookmarkManager.getVideosByTagName("video");
        ArrayList<Video> videos4 = bookmarkManager.getVideosByTagName("agile");

        assertEquals(videos1.size(), 1);
        assertEquals(videos2.size(), 1);
        assertEquals(videos3.size(), 2);
        assertEquals(videos4.size(), 0);

        assertEquals(videos1.get(0).getTitle(), video1.getTitle());
        assertEquals(videos2.get(0).getTitle(), video2.getTitle());
        assertEquals(videos3.get(0).getTitle(), video1.getTitle());
        assertEquals(videos3.get(1).getTitle(), video2.getTitle());

    }

}
