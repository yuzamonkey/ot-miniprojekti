package ot.miniprojekti.ui;

import java.util.Scanner;
import ot.miniprojekti.domain.Bookmark;
import ot.miniprojekti.logic.BookmarkManager;
import ot.miniprojekti.dao.Books;
import java.sql.SQLException;

public class TextUserInterface {

    private BookmarkManager bookmarkManager;
    private Scanner reader;
    private Books books;

    public TextUserInterface(BookmarkManager bookmarkManager, Scanner reader, Books books) {
        this.bookmarkManager = bookmarkManager;
        this.reader = reader;
        this.books = books; 
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
                addBookmark();
            } else if (answer.equals("2")) {
                printBookmarks();
            } else if (answer.equals("x") || answer.equals("")) {
                break;
            }
        }
    }

    private void addBookmark() throws SQLException {
//        System.out.print("Kirjoittaja: ");
//        String author = reader.nextLine();
        System.out.print("Otsikko: ");
        String title = reader.nextLine();
//        System.out.println("ISBN: ");
//        String isbn = reader.nextLine();
//        this.books.add(author, title, isbn);
        this.bookmarkManager.addBookmark(title);
    }

    private void printBookmarks() {
        for (Bookmark bookmark : bookmarkManager.getBookmarks()) {
            System.out.println(bookmark.getText());
        }
    }
}
