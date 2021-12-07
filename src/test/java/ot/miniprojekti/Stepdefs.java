package ot.miniprojekti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import ot.miniprojekti.domain.Bookmark;
import ot.miniprojekti.logic.BookmarkManager;

public class Stepdefs {

    private BookmarkManager manager;
    private Bookmark bm;

    @Given("Bookmark is initialized")
    public void bookmarkIsInitialized() {
        bm = new Bookmark("Foobar");
    }

    @Then("text should be correct")
    public void textShouldBeCorrect() {
        assertEquals("Foobar", bm.getText());
    }

    @When("text is updated")
    public void updateText() {
        bm.setText("Barfoo");
    }

    @Then("text should be updated")
    public void updatedTextShouldBeCorrect() {
        assertEquals("Barfoo", bm.getText());
    }

    @Given("BookmarkManager is initialized")
    public void bookmarkManagerIsInitialized() {
        manager = new BookmarkManager();
    }

    @When("bookmarks are added")
    public void addBookmarks() {
        manager.addBookmark("bm1");
        manager.addBookmark("bm2");
    }

    @Then("all bookmarks should be accessible")
    public void bookmarksShouldBeAccessible() {
        assertEquals("bm1", manager.getBookmarks().get(0).getText());
        assertEquals("bm2", manager.getBookmarks().get(1).getText());
    }
}
