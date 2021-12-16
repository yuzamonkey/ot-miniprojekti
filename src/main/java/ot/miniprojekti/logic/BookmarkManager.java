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

    public BookmarkManager(BookmarkDao bm, BookDao bo, VideoDao v, BlogDao bl, PodcastDao p) {
        this.bookmarkDao = bm;
        this.bookDao = bo;
        this.videoDao = v;
        this.blogDao = bl;
        this.podcastDao = p;
    }

    public void addBook(String author, String title, String isbn) {
        this.bookDao.add(author, title, isbn);
    }

    public void addBlog(String title, String author, String url) {
        this.blogDao.add(title, author, url);
    }

    public void addPodcast(String name, String title, String description) {
        this.podcastDao.add(name, title, description);
    }

    public void addVideo(String title, String url, String comment) {
        this.videoDao.add(title, url, comment);
    }

    public void addTag(String tag) {
        if (!tag.isEmpty()) {
            // splits a comma-separated string into a list of tags
            // and removes whitespace characters before and after the commas
            this.bookmarkDao.addTag(Arrays.asList(tag.split("[ ]*,[ ]*")));
        }
    }
    
    public ArrayList<Book> getReadBooks() {
        return this.bookDao.getRead();
    }

    public ArrayList<Book> getUnreadBooks() {
        return this.bookDao.getUnread();
    }
    
    public ArrayList<Video> getReadVideos() {
        return this.videoDao.getRead();
    }

    public ArrayList<Video> getUnreadVideos() {
        return this.videoDao.getUnread();
    }
    
    public ArrayList<Blog> getReadBlogs() {
        return this.blogDao.getRead();
    }

    public ArrayList<Blog> getUnreadBlogs() {
        return this.blogDao.getUnread();
    }
    
    public ArrayList<Podcast> getReadPodcasts() {
        return this.podcastDao.getRead();
    }

    public ArrayList<Podcast> getUnreadPodcasts() {
        return this.podcastDao.getUnread();
    }

    public ArrayList<Book> getBooksByTagName(String tag) {
        return this.bookDao.findByTag(tag);
    }

    public ArrayList<Video> getVideosByTagName(String tag) {
        return this.videoDao.findByTag(tag);
    }

    public ArrayList<Blog> getBlogsByTagName(String tag) {
        return this.blogDao.findByTag(tag);
    }

    public ArrayList<Podcast> getPodcastsByTagName(String tag) {
        return this.podcastDao.findByTag(tag);
    }

    public String deleteBookmarkById(String stringId) {
        try {
            int id = Integer.valueOf(stringId);

            this.bookmarkDao.deleteBookmarkById(id);
            this.bookmarkDao.deleteTagByBookmarkId(id);

            boolean deleted = this.podcastDao.deleteByBookmarkId(id)
                    || this.blogDao.deleteByBookmarkId(id)
                    || this.bookDao.deleteByBookmarkId(id)
                    || this.videoDao.deleteByBookmarkId(id);
            if (!deleted) {
                return "Vinkkiä ei löytynyt tällä id:llä";
            }
            return "Vinkki poistettu";
        } catch (NumberFormatException e) {
            return "Annetu id ei ollut numero";
        }
    }

    public String markBookmarkAsRead(String stringId, String comment) {
        try {
            int id = Integer.valueOf(stringId);

            if (!this.bookmarkDao.isBookmark(id)) {
                return "Vinkkiä ei löytynyt tällä id:llä";
            }
            this.bookmarkDao.markAsRead(id, comment);
            return "Vinkki merkitty luetuksi";
        } catch (NumberFormatException e) {
            return "Annetu id ei ollut numero";
        }
    }
}
