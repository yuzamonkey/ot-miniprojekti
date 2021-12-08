Feature: Tags can be added

    Scenario: Adding a tag saves the tag to a database
        Given tags is initialized
        When tag is added to database
        Then tag should be saved to database