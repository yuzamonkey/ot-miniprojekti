package ot.miniprojekti.domain;

public class Video {

    private int id;
    private String title;
    private String comment;
    private String url;

    public Video(int id, String title, String url, String comment) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.comment = comment;
    }

    @Override
    public String toString() {
        String s = (id + 1) + ". " + title + "\n" + "url: " + url + "\n" + "kommentti: " + comment + "\n";
        return s;
    }
}
