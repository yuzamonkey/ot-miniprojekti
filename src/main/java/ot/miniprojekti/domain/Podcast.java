package ot.miniprojekti.domain;

public class Podcast {

    private int id;
    private String title;
    private String name;
    private String description;
    private boolean isRead;
    private String note;

    public Podcast(int id, String title, String name, String description, boolean read) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.description = description;
        this.isRead = read;
    }

    public int getId() {
        return this.id;
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
            return "[" + id + "] " + title + "\nTekijä: " + name + "\nKuvaus: " + description + "\nMuistiinpano: " + note;
        } else {
            return "[" + id + "] " + title + "\nTekijä: " + name + "\nKuvaus: " + description;
        }
    }
}
