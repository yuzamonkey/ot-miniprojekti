package ot.miniprojekti.ui;

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

    public void start() {
        while (true) {
            System.out.println("Valitse toiminto:");
            System.out.println("[1] Lisää vinkki");
            System.out.println("[2] Tulosta vinkit");
            System.out.println("[3] Hae tagin perusteella");
            System.out.println("[4] Poista vinkki");
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
            } else if (answer.equals("3")) {
                System.out.print("Tagi: ");
                String tag = reader.nextLine();
                searchBooksByTagName(tag);
                searchVideosByTagName(tag);
                searchBlogsByTagName(tag);
                searchPodcastsByTagName(tag);
            } else if (answer.equals("4")) {
                printBooks();
                printVideos();
                printBlogs();
                printPodcasts();
                System.out.println("Kirjoita poistettavan vinkin id");
                System.out.print("> ");
                String id = reader.nextLine();
                if (this.bookmarkManager.deleteBookmarkById(id)) {
                    System.out.println("Vinkki jonka id oli " + id + " poistettu");
                } else {
                    System.out.println("Vinkkiä ei voitu poistaa");
                }
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void addBookmark() {
        boolean jatkaLooppia = true;

        while (jatkaLooppia) {
            System.out.println("Valitse tyyppi:");
            System.out.println("[1] Lisää kirja");
            System.out.println("[2] Lisää video");
            System.out.println("[3] Lisää blogi");
            System.out.println("[4] Lisää podcast");

            System.out.print("> ");
            String answer = reader.nextLine();

            switch(answer) {
                case "1":
                    addBook();
                    jatkaLooppia = false;
                    break;
                case "2":
                    addVideo();
                    jatkaLooppia = false;
                    break;
                case "3":
                    addBlog();
                    jatkaLooppia = false;
                    break;
                case "4":
                    addPodcast();
                    jatkaLooppia = false;
                    break;
                default:
                    System.out.println("\n== Valintaa ei tunnistettu, valitse numero väliltä 1-4 ==\n");
            }
        }

        addTag();
    }

    private void addBook() {
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("ISBN: ");
        String isbn = reader.nextLine();
        this.bookmarkManager.addBook(author, title, isbn);
    }

    private void addVideo() {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        System.out.print("Kommentti: ");
        String comment = reader.nextLine();
        this.bookmarkManager.addVideo(title, url, comment);
    }

    private void addBlog() {
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kirjoittaja: ");
        String author = reader.nextLine();
        System.out.print("Url: ");
        String url = reader.nextLine();
        this.bookmarkManager.addBlog(title, author, url);
    }

    private void addPodcast() {
        System.out.print("Tekijän nimi: ");
        String name = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
        System.out.print("Kuvaus: ");
        String description = reader.nextLine();
        this.bookmarkManager.addPodcast(name, title, description);
    }

    private void addTag() {
        System.out.print("Tagit: ");
        String tag = reader.nextLine();
        this.bookmarkManager.addTag(tag);
    }

    private void printBooks() {
        System.out.println("Kirjat:");
        for (Book b : bookmarkManager.getBooks()) {
            System.out.println(b.toString());
        }
    }

    private void printVideos() {
        System.out.println("Videot:");
        for (Video v : bookmarkManager.getVideos()) {
            System.out.println(v.toString());
        }
    }

    private void printBlogs() {
        System.out.println("Blogit:");
        for (Blog b : bookmarkManager.getBlogs()) {
            System.out.println(b.toString());
        }
    }

    private void printPodcasts() {
        System.out.println("Podcastit:");
        for (Podcast p : bookmarkManager.getPodcasts()) {
            System.out.println(p.toString());
        }
    }

    private void searchBooksByTagName(String tag) {
        for (Book b : bookmarkManager.getBooksByTagName(tag)) {
            System.out.println(b.toString());
        }
    }

    private void searchVideosByTagName(String tag) {
        for (Video v : bookmarkManager.getVideosByTagName(tag)) {
            System.out.println(v.toString());
        }
    }

    private void searchBlogsByTagName(String tag) {
        for (Blog b : bookmarkManager.getBlogsByTagName(tag)) {
            System.out.println(b.toString());
        }
    }

    private void searchPodcastsByTagName(String tag) {
        for (Podcast p : bookmarkManager.getPodcastsByTagName(tag)) {
            System.out.println(p.toString());
        }
    }
}
