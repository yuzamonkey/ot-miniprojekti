package ot.miniprojekti.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import ot.miniprojekti.dao.Blogs;
import ot.miniprojekti.dao.Books;
import ot.miniprojekti.dao.Podcasts;
import ot.miniprojekti.dao.Tags;
import ot.miniprojekti.dao.Videos;
import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;

public class BookmarkManager {

    private Books books;
    private Videos videos;
    private Blogs blogs;
    private Podcasts podcasts;
    private Tags tags;

    public BookmarkManager(Books books, Videos videos, Blogs blogs, Podcasts podcasts, Tags tags) {
        this.books = books;
        this.videos = videos;
        this.blogs = blogs;
        this.podcasts = podcasts;
        this.tags = tags;
    }

    public void addBook(String author, String title, String isbn) throws SQLException {
        this.books.add(author, title, isbn);
    }

    public void addVideo(String title, String url, String comment) throws SQLException {
        this.videos.add(title, url, comment);
    }

    public void addBlog(String title, String author, String url) throws SQLException {
        this.blogs.add(title, author, url);
    }

    public void addPodcast(String name, String title, String description) throws SQLException {
        this.podcasts.add(name, title, description);
    }

    public void addTag(String tag, String type) throws SQLException {
        if (!tag.isEmpty()) {
            String bookmark = "";
            if (type.equals("1")) {
                bookmark = "books";
            } else if (type.equals("2")) {
                bookmark = "videos";
            } else if (type.equals("3")) {
                bookmark = "blogs";
            } else if (type.equals("4")) {
                bookmark = "podcasts";
            }
            // splits a comma-separated string into a list of tags
            // and removes whitespace characters before and after the commas
            this.tags.addTag(Arrays.asList(tag.split("[ ]*,[ ]*")), bookmark);
        }
    }

    public ArrayList<Book> getBooks() throws SQLException {
        return this.books.getAll();
    }

    public ArrayList<Video> getVideos() throws SQLException {
        return this.videos.getAll();
    }

    public ArrayList<Blog> getBlogs() throws SQLException {
        return this.blogs.getAll();
    }

    public ArrayList<Podcast> getPodcasts() throws SQLException {
        return this.podcasts.getAll();
    }
}
