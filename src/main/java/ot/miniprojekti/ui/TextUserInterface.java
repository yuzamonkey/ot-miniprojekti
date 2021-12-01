package ot.miniprojekti.ui;

import java.util.Scanner;
import ot.miniprojekti.domain.Bookmark;
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

    private void addBookmark() {
        System.out.print("Nimi: ");
        String name = reader.nextLine();
        this.bookmarkManager.addBookmark(name);
    }

    private void printBookmarks() {
        for (Bookmark bookmark : bookmarkManager.getBookmarks()) {
            System.out.println(bookmark.getText());
        }
    }
}
