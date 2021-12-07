package ot.miniprojekti.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.dao.Books;
import ot.miniprojekti.dao.Podcasts;

public class BookmarkManager {

    private Books books;
    private Podcasts podcasts;

    public BookmarkManager(Books books, Podcasts podcasts) {
        this.books = books;
        this.podcasts = podcasts;
    }

    public void addBook(String author, String title, String isbn) throws SQLException {
        this.books.add(author, title, isbn);
    }

    public void addBlog() {
    }

    public void addPodcast(String name, String title, String description) throws SQLException {
        this.podcasts.add(name, title, description);
    }

    public void addVideo() {
    }

    public void addTag() {
        
    }

    public ArrayList<Book> getBooks() throws SQLException {
        return this.books.getAll();
    }

    public ArrayList<Podcast> getPodcasts() throws SQLException {
        return this.podcasts.getAll();
    }

}
