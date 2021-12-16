package ot.miniprojekti.domain;

public class Blog {

    private int id;
    private String title;
    private String author;
    private String url;
    private boolean isRead;
    private String note;

    public Blog(int id, String title, String author, String url, boolean read) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.isRead = read;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getUrl() {
        return this.url;
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
            return "[" + id + "] " + title + "\nTekijä: " + author + "\nURL: " + url + "\nMuistiinpano: " + note;
        } else {
            return "[" + id + "] " + title + "\nTekijä: " + author + "\nURL: " + url;
        }
    }
}
