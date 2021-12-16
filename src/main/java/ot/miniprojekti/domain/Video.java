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
        String s = title + "\nurl: " + url + "\nkommentti: " + comment + "\nid: " + id;
        if (isRead) {
            s = title + "\nurl: " + url + "\nkommentti: " + comment + "\nmuistiinpano: " + note + "\nid: " + id;
        }
        return s;
    }
}
