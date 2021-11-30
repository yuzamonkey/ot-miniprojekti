
package ot.miniprojekti;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import ot.miniprojekti.domain.Bookmark;

import static org.junit.Assert.*;

public class Stepdefs {
    @Then("dummy test")
    public void dummyTest() {
        assertTrue(true);
    }

    Bookmark bm;
    int value = 0;

    @Given("Bookmark is initialized")
    public void bookmarkIsInitialized() {
        bm = new Bookmark();
    }

    @When("method is called")
    public void callMethod() {
        value = bm.returnSomething();
    }

    @Then("the value should be 1")
    public void valueShouldBeOne() {
        assertEquals(1, value);
    }

}