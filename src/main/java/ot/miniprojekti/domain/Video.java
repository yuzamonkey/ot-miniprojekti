package ot.miniprojekti.domain;

public class Video {

    private int id;
    private String title;
    private String comment;
    private String url;
    private boolean isRead;
    private String note;

    public Video(int id, String title, String url, String comment, boolean read) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.comment = comment;
        this.isRead = read;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getComment() {
        return this.comment;
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
            return "[" + id + "] " + title + "\nURL: " + url + "\nKommentti: " + comment + "\nMuistiinpano: " + note;
        } else {
            return "[" + id + "] " + title + "\nURL: " + url + "\nKommentti: " + comment;
        }
    }
}
