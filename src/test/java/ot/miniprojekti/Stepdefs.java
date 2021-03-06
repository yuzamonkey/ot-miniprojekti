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

public class Stepdefs {

    private BookmarkDao bookmarkDao;
    private BlogDao blogDao;
    private BookDao bookDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;

    @Before
    public void setUp() {
        bookmarkDao = new BookmarkDao("test.db");
        bookmarkDao.deleteRows();
    }

    @Given("books is initialized")
    public void booksIsInitialized() {
        bookDao = new BookDao("test.db");
        bookDao.deleteRows();
    }

    @When("book is added to database")
    public void bookIsAddedToDatabase() {
        bookDao.add("Arthur the Author", "How to write a book", "951-98548-9-4");
    }

    @Then("book should be saved to database")
    public void bookShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Arthur the Author", bookDao.getUnread().get(0).getAuthor());
        assertEquals("How to write a book", bookDao.getUnread().get(0).getTitle());
        assertEquals("951-98548-9-4", bookDao.getUnread().get(0).getISBN());
    }

    @Given("blogs is initialized")
    public void blogsIsInitialized() {
        blogDao = new BlogDao("test.db");
        blogDao.deleteRows();
    }

    @When("blog is added to database")
    public void blogIsAddedToDatabase() {
        blogDao.add("Writing a blog", "Beatrice the Blogger", "www.blogs.com/beatricesblog");
    }

    @Then("blog should be saved to database")
    public void blogShouldBeSavedToDatabase() {
        assertEquals("Writing a blog", blogDao.getUnread().get(0).getTitle());
        assertEquals("Beatrice the Blogger", blogDao.getUnread().get(0).getAuthor());
        assertEquals("www.blogs.com/beatricesblog", blogDao.getUnread().get(0).getUrl());
    }

    @Given("podcasts is initialized")
    public void podcastsIsInitialized() {
        podcastDao = new PodcastDao("test.db");
        podcastDao.deleteRows();
    }

    @When("podcast is added to database")
    public void podcastIsAddedToDatabase() {
        podcastDao.add("Podcast about vegetables", "How carrots grow",
                "In this episode experts dig deep into the world of carrots.");
    }

    @Then("podcast should be saved to database")
    public void podcastShouldBeSavedToDatabase() {
        assertEquals("Podcast about vegetables", podcastDao.getUnread().get(0).getName());
        assertEquals("How carrots grow", podcastDao.getUnread().get(0).getTitle());
        assertEquals("In this episode experts dig deep into the world of carrots.",
                podcastDao.getUnread().get(0).getDescription());
    }

    @Given("videos is initialized")
    public void videosIsInitialized() {
        videoDao = new VideoDao("test.db");
        videoDao.deleteRows();
    }

    @When("video is added to database")
    public void videoIsAddedToDatabase() {
        videoDao.add("Washing dishes", "www.videos.com/washing_dishes", "A nice video about washing dishes");
    }

    @Then("video should be saved to database")
    public void videoShouldBeSavedToDatabase() {
        assertEquals("Washing dishes", videoDao.getUnread().get(0).getTitle());
        assertEquals("A nice video about washing dishes", videoDao.getUnread().get(0).getComment());
        assertEquals("www.videos.com/washing_dishes", videoDao.getUnread().get(0).getUrl());
    }
}
