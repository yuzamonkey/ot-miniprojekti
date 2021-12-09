package ot.miniprojekti;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import ot.miniprojekti.dao.BlogDao;
import ot.miniprojekti.dao.BookDao;
import ot.miniprojekti.dao.BookmarkDao;
import ot.miniprojekti.dao.PodcastDao;
import ot.miniprojekti.dao.VideoDao;
import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;

public class Stepdefs {

    private Blog blog;
    private Book book;
    private Podcast podcast;
    private Video video;
    private BookmarkDao bookmarkDao;
    private BlogDao blogDao;
    private BookDao bookDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;

    @Before
    public void setUp() {
        bookmarkDao = new BookmarkDao("test.db");
    }

    @Given("blog is initialized")
    public void blogIsInitialized() {
        blog = new Blog(123, "Overreacted", "Dan Abramov", "https://overreacted.io/");
    }

    @Then("blog fields should be correct")
    public void blogFieldsShouldBeCorrect() {
        assertEquals(123, blog.getId());
        assertEquals("Overreacted", blog.getTitle());
        assertEquals("Dan Abramov", blog.getAuthor());
        assertEquals("https://overreacted.io/", blog.getUrl());
    }

    @Given("book is initialized")
    public void bookIsInitialized() {
        book = new Book("Martin Fowler", "Refactoring", "9780201485677");
    }

    @Then("book fields should be correct")
    public void bookFieldsShouldBeCorrect() {
        assertEquals("Martin Fowler", book.getAuthor());
        assertEquals("Refactoring", book.getTitle());
        assertEquals("9780201485677", book.getISBN());
    }

    @Given("podcast is initialized")
    public void podcastIsInitialized() {
        podcast = new Podcast("Machine Learning Guide", "OCDevel", "Machine learning fundamentals");
    }

    @Then("podcast fields should be correct")
    public void podcastFieldsShouldBeCorrect() {
        assertEquals("Machine Learning Guide", podcast.getTitle());
        assertEquals("OCDevel", podcast.getName());
        assertEquals("Machine learning fundamentals", podcast.getDescription());
    }

    @Given("video is initialized")
    public void videoIsInitialized() {
        video = new Video(123, "Java in 100 Seconds", "https://www.youtube.com/watch?v=l9AzO1FMgM8", "Good vid");
    }

    @Then("video fields should be correct")
    public void videoFieldsShouldBeCorrect() {
        assertEquals(123, video.getId());
        assertEquals("Java in 100 Seconds", video.getTitle());
        assertEquals("https://www.youtube.com/watch?v=l9AzO1FMgM8", video.getUrl());
        assertEquals("Good vid", video.getComment());
    }

    @Given("books is initialized")
    public void booksIsInitialized() {
        bookDao = new BookDao("test.db");
    }

    @When("book is added to database")
    public void bookIsAddedToDatabase() {
        bookDao.add("Arthur the Author", "How to write a book", "951-98548-9-4");
    }

    @Then("book should be saved to database")
    public void bookShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Arthur the Author", bookDao.getAll().get(0).getAuthor());
        assertEquals("How to write a book", bookDao.getAll().get(0).getTitle());
        assertEquals("951-98548-9-4", bookDao.getAll().get(0).getISBN());
    }

    @Given("blogs is initialized")
    public void blogsIsInitialized() {
        blogDao = new BlogDao("test.db");
    }

    @When("blog is added to database")
    public void blogIsAddedToDatabase() {
        blogDao.add("Writing a blog", "Beatrice the Blogger", "www.blogs.com/beatricesblog");
    }

    @Then("blog should be saved to database")
    public void blogShouldBeSavedToDatabase() {
        assertEquals("Writing a blog", blogDao.getAll().get(0).getTitle());
        assertEquals("Beatrice the Blogger", blogDao.getAll().get(0).getAuthor());
        assertEquals("www.blogs.com/beatricesblog", blogDao.getAll().get(0).getUrl());
    }

    @Given("podcasts is initialized")
    public void podcastsIsInitialized() {
        podcastDao = new PodcastDao("test.db");
    }

    @When("podcast is added to database")
    public void podcastIsAddedToDatabase() {
        podcastDao.add("Podcast about vegetables", "How carrots grow",
                "In this episode experts dig deep into the world of carrots.");
    }

    @Then("podcast should be saved to database")
    public void podcastShouldBeSavedToDatabase() {
        assertEquals("Podcast about vegetables", podcastDao.getAll().get(0).getName());
        assertEquals("How carrots grow", podcastDao.getAll().get(0).getTitle());
        assertEquals("In this episode experts dig deep into the world of carrots.",
                podcastDao.getAll().get(0).getDescription());
    }

    @Given("videos is initialized")
    public void videosIsInitialized() {
        videoDao = new VideoDao("test.db");
    }

    @When("video is added to database")
    public void videoIsAddedToDatabase() {
        videoDao.add("Washing dishes", "www.videos.com/washing_dishes", "A nice video about washing dishes");
    }

    @Then("video should be saved to database")
    public void videoShouldBeSavedToDatabase() {
        assertEquals("Washing dishes", videoDao.getAll().get(0).getTitle());
        assertEquals("A nice video about washing dishes", videoDao.getAll().get(0).getComment());
        assertEquals("www.videos.com/washing_dishes", videoDao.getAll().get(0).getUrl());
    }
}
