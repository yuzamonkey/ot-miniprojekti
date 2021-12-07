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

    @Override
    public String toString() {
        String s = title + "\n" + "tekijä: " + author + "\n" + "isbn: " + isbn + "\n";
        return s;
    }

}
