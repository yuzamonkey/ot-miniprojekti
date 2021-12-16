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
    
    public void setRead(boolean read) {
        this.isRead = read;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        String s = title + "\ntekijä: " + author + "\nurl: " + url + "\nid: " + id + "\n";
        if (isRead) {
            s += "\nmuistiinpano: " + note;
        }
        return s;
    }
}
