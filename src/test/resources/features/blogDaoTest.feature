Feature: As a user I can add a blog

    Scenario: Adding a blog saves the blog to a database
        Given blogs is initialized
        When blog is added to database
        Then blog should be saved to database
