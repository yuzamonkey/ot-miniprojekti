package ot.miniprojekti.domain;

public class Book {

    private int id;
    private String author;
    private String title;
    private String isbn;
    private boolean isRead;
    private String note;

    public Book(int id, String author, String title, String isbn, boolean read) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.isRead = read;
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
    
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
        if (note == null) {
            this.note = "-";
        }
    }

    @Override
    public String toString() {
        if (isRead) {
            return "[" + id + "] " + title + "\nTekijä: " + author + "\nISBN: " + isbn + "\nMuistiinpano: " + note;
        } else {
            return "[" + id + "] " + title + "\nTekijä: " + author + "\nISBN: " + isbn;
        }
    }
}
