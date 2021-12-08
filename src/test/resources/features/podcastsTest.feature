Feature: As a user I can add a podcast

    Scenario: Adding a podcast saves the podcast to a database
        Given podcasts is initialized
        When podcast is added to database
        Then podcast should be saved to database
