package ot.miniprojekti.domain;

public class Podcast {
    private int id;
    private String title;
    private String name;
    private String description;

    public Podcast(int id, String title, String name, String description) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        String s = (id + 1) + ". " + title + "\n" + "tekijä: " + name + "\n" + "kuvaus: " + description + "\n";
        return s;
    }

}
