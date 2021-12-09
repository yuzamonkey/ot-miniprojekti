package ot.miniprojekti.logic;

import java.util.ArrayList;
import java.util.Arrays;
import ot.miniprojekti.dao.BlogDao;
import ot.miniprojekti.dao.BookDao;
import ot.miniprojekti.dao.BookmarkDao;
import ot.miniprojekti.dao.PodcastDao;
import ot.miniprojekti.dao.VideoDao;
import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;

public class BookmarkManager {

    private BookmarkDao bookmarkDao;
    private BookDao bookDao;
    private VideoDao videoDao;
    private BlogDao blogDao;
    private PodcastDao podcastDao;

    public BookmarkManager(BookmarkDao bookmarkDao, BookDao bookDao, VideoDao videoDao, BlogDao blogDao, PodcastDao podcastDao) {
        this.bookmarkDao = bookmarkDao;
        this.bookDao = bookDao;
        this.videoDao = videoDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
    }

    public void addBook(String author, String title, String isbn) {
        this.bookDao.add(author, title, isbn);
    }

    public void addVideo(String title, String url, String comment) {
        this.videoDao.add(title, url, comment);
    }

    public void addBlog(String title, String author, String url) {
        this.blogDao.add(title, author, url);
    }

    public void addPodcast(String name, String title, String description) {
        this.podcastDao.add(name, title, description);
    }

    public void addTag(String tag) {
        if (!tag.isEmpty()) {
            // splits a comma-separated string into a list of tags
            // and removes whitespace characters before and after the commas
            this.bookmarkDao.addTag(Arrays.asList(tag.split("[ ]*,[ ]*")));
        }
    }

    public ArrayList<Book> getBooks() {
        return this.bookDao.getAll();
    }
    
    public ArrayList<Book> getBooksByTag(String tag) {
        return bookmarkDao.findBooksByTag(tag);
    }

    public ArrayList<Video> getVideos() {
        return this.videoDao.getAll();
    }

    public ArrayList<Blog> getBlogs() {
        return this.blogDao.getAll();
    }

    public ArrayList<Podcast> getPodcasts() {
        return this.podcastDao.getAll();
    }
}
