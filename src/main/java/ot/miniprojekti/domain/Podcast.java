package ot.miniprojekti.domain;

public class Podcast {
    private String title;
    private String name;
    private String description;

    public Podcast(String title, String name, String description) {
        this.title = title;
        this.name = name;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String s = title + "\n" + "tekijä: " + name + "\n" + "kuvaus: " + description + "\n";
        return s;
    }

}
