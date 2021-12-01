Feature: As a user I want to save bookmarks

    Scenario: Added bookmarks can be saved
        Given BookmarkManager is initialized
        When bookmarks are added
        Then all bookmarks should be accessible
