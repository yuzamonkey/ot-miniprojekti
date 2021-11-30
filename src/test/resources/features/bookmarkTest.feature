Feature: As a user I want to have the tests working

    Scenario: Adding bookmark adds correct text
        Given Bookmark is initialized
        Then text should be correct

    Scenario: Updating text updates text
        Given Bookmark is initialized
        When text is updated
        Then text should be updated
