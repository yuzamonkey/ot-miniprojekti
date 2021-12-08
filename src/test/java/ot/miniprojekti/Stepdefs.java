package ot.miniprojekti;

import java.sql.SQLException;
import java.util.ArrayList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import ot.miniprojekti.logic.BookmarkManager;

import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;

import ot.miniprojekti.dao.Blogs;
import ot.miniprojekti.dao.Books;
import ot.miniprojekti.dao.Podcasts;
import ot.miniprojekti.dao.Videos;
import ot.miniprojekti.dao.Tags;

public class Stepdefs {

    // private BookmarkManager manager;
    private Blog blog;
    private Book book;
    private Podcast podcast;
    private Video video;
    private Blogs blogs;
    private Books books;
    private Podcasts podcasts;
    private Videos videos;
    private Tags tags;
    
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
    public void booksIsInitialized() throws SQLException {
        books = new Books("test.db");
    }

    @When("book is added to database")
    public void bookIsAddedToDatabase() throws SQLException {
        books.add("Arthur the Author", "How to write a book", "951-98548-9-4");
    }
    
    @Then("book should be saved to database")
    public void bookShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Arthur the Author", books.getAll().get(0).getAuthor());
        assertEquals("How to write a book", books.getAll().get(0).getTitle());
        assertEquals("951-98548-9-4", books.getAll().get(0).getISBN());
    }
    
    @Given("blogs is initialized")
    public void blogsIsInitialized() throws SQLException {
        blogs = new Blogs("test.db");
    }

    @When("blog is added to database")
    public void blogIsAddedToDatabase() throws SQLException {
        blogs.add("Writing a blog", "Beatrice the Blogger", "www.blogs.com/beatricesblog");
    }
    
    @Then("blog should be saved to database")
    public void blogShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Writing a blog", blogs.getAll().get(0).getTitle());
        assertEquals("Beatrice the Blogger", blogs.getAll().get(0).getAuthor());
        assertEquals("www.blogs.com/beatricesblog", blogs.getAll().get(0).getUrl());
    }
    
    @Given("podcasts is initialized")
    public void podcastsIsInitialized() throws SQLException {
        podcasts = new Podcasts("test.db");
    }

    @When("podcast is added to database")
    public void podcastIsAddedToDatabase() throws SQLException {
        podcasts.add("Podcast about vegetables", "How carrots grow",
             "In this episode experts dig deep into the world of carrots.");
    }
    
    @Then("podcast should be saved to database")
    public void podcastShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Podcast about vegetables", podcasts.getAll().get(0).getName());
        assertEquals("How carrots grow", podcasts.getAll().get(0).getTitle());
        assertEquals("In this episode experts dig deep into the world of carrots.",
             podcasts.getAll().get(0).getDescription());
    }
    
    @Given("videos is initialized")
    public void videosIsInitialized() throws SQLException {
        videos = new Videos("test.db");
    }

    @When("video is added to database")
    public void videoIsAddedToDatabase() throws SQLException {
        videos.add("Washing dishes", "www.videos.com/washing_dishes", "A nice video about washing dishes");
    }
    
    @Then("video should be saved to database")
    public void videoShouldBeSavedToDatabase() throws SQLException {
        assertEquals("Washing dishes", videos.getAll().get(0).getTitle());
        assertEquals("A nice video about washing dishes", videos.getAll().get(0).getComment());
        assertEquals("www.videos.com/washing_dishes", videos.getAll().get(0).getUrl());
    }

    @Given("tags is initialized")
    public void tagsIsInitialized() throws SQLException {
        tags = new Tags("test.db");
    }

    @When("tag is added to database")
    public void tagIsAddedToDatabase() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        list.add("testTag1");
        tags.addTag(list, "books");
    }
    
    @Then("tag should be saved to database")
    public void tagShouldBeSavedToDatabase() throws SQLException {
        assertNotNull(tags.tagIdByName("testTag1"));
    }
    
    // @Given("BookmarkManager is initialized")
    // public void bookmarkManagerIsInitialized() {
    // manager = new BookmarkManager();
    // }

    // @When("bookmarks are added")
    // public void addBookmarks() {
    // manager.addBookmark("bm1");
    // manager.addBookmark("bm2");
    // }

    // @Then("all bookmarks should be accessible")
    // public void bookmarksShouldBeAccessible() {
    // assertEquals("bm1", manager.getBookmarks().get(0).getText());
    // assertEquals("bm2", manager.getBookmarks().get(1).getText());
    // }
}
