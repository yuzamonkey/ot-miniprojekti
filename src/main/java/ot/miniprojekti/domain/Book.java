package ot.miniprojekti.domain;

public class Book {

    private int id;
    private String author;
    private String title;
    private String isbn;
    private boolean isRead;
    private String note;

    public Book(int id, String author, String title, String isbn) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public int getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        String s = title + "\ntekijä: " + author + "\nisbn: " + isbn + "\nid: " + id;
        if (isRead) {
            s += "\nmuistiinpano: " + note;
        }
        return s;
    }

}
