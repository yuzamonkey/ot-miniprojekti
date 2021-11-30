
package ot.miniprojekti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import ot.miniprojekti.domain.Bookmark;

import static org.junit.Assert.*;

public class Stepdefs {
    Bookmark bm;

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
}