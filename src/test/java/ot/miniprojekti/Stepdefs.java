package ot.miniprojekti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import ot.miniprojekti.domain.Book;
//import ot.miniprojekti.logic.BookmarkManager;

public class Stepdefs {

    //private BookmarkManager manager;
    private Book book;

    @Given("book is initialized")
    public void bookIsInitialized() {
        book = new Book("Martin Fowler", "Refactoring", "9780201485677");
    }

    @Then("fields should be correct")
    public void fieldsShouldBeCorrect() {
        assertEquals("Martin Fowler", book.getAuthor());
        assertEquals("Refactoring", book.getTitle());
        assertEquals("9780201485677", book.getISBN());
    }

    // @Given("BookmarkManager is initialized")
    // public void bookmarkManagerIsInitialized() {
    //     manager = new BookmarkManager();
    // }

    // @When("bookmarks are added")
    // public void addBookmarks() {
    //     manager.addBookmark("bm1");
    //     manager.addBookmark("bm2");
    // }

    // @Then("all bookmarks should be accessible")
    // public void bookmarksShouldBeAccessible() {
    //     assertEquals("bm1", manager.getBookmarks().get(0).getText());
    //     assertEquals("bm2", manager.getBookmarks().get(1).getText());
    // }
}
