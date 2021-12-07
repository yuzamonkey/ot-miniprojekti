package ot.miniprojekti.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.dao.Blogs;
import ot.miniprojekti.dao.Books;
import ot.miniprojekti.dao.Podcasts;
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

    public BookmarkManager(Books books, Videos videos, Blogs blogs, Podcasts podcasts) {
        this.books = books;
        this.videos = videos;
        this.blogs = blogs;
        this.podcasts = podcasts;
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

    public void addTag() {

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
