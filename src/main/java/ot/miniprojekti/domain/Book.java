package ot.miniprojekti.domain;

public class Book {

    private int id;
    private String author;
    private String title;
    private String isbn;

    public Book(int id, String author, String title, String isbn) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
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

    @Override
    public String toString() {
        String s = title + "\ntekijä: " + author + "\nisbn: " + isbn + "\nid: " + id;
        return s;
    }

}
