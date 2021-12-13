package ot.miniprojekti.domain;

public class Blog {

    private int id;
    private String title;
    private String author;
    private String url;

    public Blog(int id, String title, String author, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
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

    @Override
    public String toString() {
        String s = title + "\ntekijä: " + author + "\nurl: " + url + "\nid: " + id + "\n";
        return s;
    }
}
