package ot.miniprojekti.domain;

public class Podcast {

    private int id;
    private String title;
    private String name;
    private String description;
    private boolean isRead;
    private String note;

    public Podcast(int id, String title, String name, String description) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.description = description;
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
    
    public void setRead(boolean read) {
        this.isRead = read;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        String s = title + "\ntekijä: " + name + "\nkuvaus: " + description + "\nid: " + id;
        if (isRead) {
            s += "\nmuistiinpano: " + note;
        }
        return s;
    }

}
