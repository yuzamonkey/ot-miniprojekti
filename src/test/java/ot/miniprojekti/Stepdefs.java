package ot.miniprojekti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

//import ot.miniprojekti.logic.BookmarkManager;

import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;

public class Stepdefs {

    // private BookmarkManager manager;
    private Blog blog;
    private Book book;
    private Podcast podcast;
    private Video video;

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
