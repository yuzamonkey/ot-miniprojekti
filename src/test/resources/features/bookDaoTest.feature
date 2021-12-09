Feature: As a user I can add a book

    Scenario: Adding a book saves the book to a database
        Given books is initialized
        When book is added to database
        Then book should be saved to database
