package ot.miniprojekti.ui;

import java.util.Scanner;
import ot.miniprojekti.logic.BookmarkManager;
import java.sql.SQLException;

import ot.miniprojekti.domain.Book;
import ot.miniprojekti.domain.Podcast;

public class TextUserInterface {

    private BookmarkManager bookmarkManager;
    private Scanner reader;

    public TextUserInterface(BookmarkManager bookmarkManager, Scanner reader) {
        this.bookmarkManager = bookmarkManager;
        this.reader = reader;
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println("Valitse toiminto");
            System.out.println("[1] Lisää vinkki");
            System.out.println("[2] Tulosta vinkit");
            System.out.println("[x] Sulje sovellus");

            System.out.print("> ");
            String answer = reader.nextLine();

            if (answer.equals("1")) {
                System.out.println("[1] Lisää kirja");
                System.out.println("[2] Lisää Podcast");

                System.out.print("> ");
                String addType = reader.nextLine();

                if (addType.equals("1")) {
                    addBookmark();
                } else if (addType.equals("2")) {
                    addPodcast();
                }

            } else if (answer.equals("2")) {
                printBooks();
                printPodcasts();
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void addBookmark() throws SQLException {
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.println("ISBN: ");
        String isbn = reader.nextLine();
        this.bookmarkManager.addBook(author, title, isbn);
    }

    private void addPodcast() throws SQLException {
        System.out.print("Tekijän nimi: ");
        String name = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.println("Kuvaus: ");
        String description = reader.nextLine();
        this.bookmarkManager.addPodcast(name, title, description);
    }

    private void printBooks() throws SQLException {
        System.out.println("Kirjat");
        // for (Podcast p : podcasts.getAll()) {
        for (Book b : bookmarkManager.getBooks()) {
            System.out.println(b.toString());
        }
    }

    private void printPodcasts() throws SQLException {
        System.out.println("Podcastit");
        // for (Podcast p : podcasts.getAll()) {
        for (Podcast p : bookmarkManager.getPodcasts()) {
            System.out.println(p.toString());
        }
    }
}
