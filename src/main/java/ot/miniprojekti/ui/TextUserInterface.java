package ot.miniprojekti.ui;

import java.sql.SQLException;
import java.util.Scanner;
import ot.miniprojekti.domain.Blog;
import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;
import ot.miniprojekti.domain.Video;
import ot.miniprojekti.logic.BookmarkManager;

public class TextUserInterface {

    private BookmarkManager bookmarkManager;
    private Scanner reader;

    public TextUserInterface(BookmarkManager bookmarkManager, Scanner reader) {
        this.bookmarkManager = bookmarkManager;
        this.reader = reader;
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println("Valitse toiminto:");
            System.out.println("[1] Lisää vinkki");
            System.out.println("[2] Tulosta vinkit");
            System.out.println("[x] Sulje sovellus");

            System.out.print("> ");
            String answer = reader.nextLine();

            if (answer.equals("1")) {
                addBookmark();
            } else if (answer.equals("2")) {
                printBooks();
                printVideos();
                printBlogs();
                printPodcasts();
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void addBookmark() throws SQLException {
        System.out.println("Valitse tyyppi:");
        System.out.println("[1] Lisää kirja");
        System.out.println("[2] Lisää video");
        System.out.println("[3] Lisää blogi");
        System.out.println("[4] Lisää podcast");

        System.out.print("> ");
        String answer = reader.nextLine();

        if (answer.equals("1")) {
            addBook();
        } else if (answer.equals("2")) {
            addVideo();
        } else if (answer.equals("3")) {
            addBlog();
        } else if (answer.equals("4")) {
            addPodcast();
        }
    }

    private void addBook() throws SQLException {
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("ISBN: ");
        String isbn = reader.nextLine();
        this.bookmarkManager.addBook(author, title, isbn);
    }

    private void addVideo() throws SQLException {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        System.out.print("Kommentti: ");
        String comment = reader.nextLine();
        this.bookmarkManager.addVideo(title, url, comment);
    }

    private void addBlog() throws SQLException {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        this.bookmarkManager.addBlog(title, author, url);
    }

    private void addPodcast() throws SQLException {
        System.out.print("Tekijän nimi: ");
        String name = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kuvaus: ");
        String description = reader.nextLine();
        this.bookmarkManager.addPodcast(name, title, description);
    }

    private void printBooks() throws SQLException {
        System.out.println("Kirjat:");
        for (Book b : bookmarkManager.getBooks()) {
            System.out.println(b.toString());
        }
    }

    private void printVideos() throws SQLException {
        System.out.println("Videot:");
        for (Video v : bookmarkManager.getVideos()) {
            System.out.println(v.toString());
        }
    }

    private void printBlogs() throws SQLException {
        System.out.println("Blogit:");
        for (Blog b : bookmarkManager.getBlogs()) {
            System.out.println(b.toString());
        }
    }

    private void printPodcasts() throws SQLException {
        System.out.println("Podcastit:");
        for (Podcast p : bookmarkManager.getPodcasts()) {
            System.out.println(p.toString());
        }
    }
}
