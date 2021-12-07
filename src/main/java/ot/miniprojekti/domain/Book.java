package ot.miniprojekti.domain;

public class Book {

    private String author;
    private String title;
    private String isbn;

    public Book(String author, String title, String isbn) {
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
        String s = title + "\n" + "tekijä: " + author + "\n" + "isbn: " + isbn + "\n";
        return s;
    }

}
