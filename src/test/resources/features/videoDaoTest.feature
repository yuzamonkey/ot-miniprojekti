Feature: As a user I can add a video

    Scenario: Adding a video saves the video to a database
        Given videos is initialized
        When video is added to database
        Then video should be saved to database
