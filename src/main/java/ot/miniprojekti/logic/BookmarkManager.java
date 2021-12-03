package ot.miniprojekti.logic;

import java.util.ArrayList;
import java.util.List;
import ot.miniprojekti.domain.Bookmark;

public class BookmarkManager {

    private List<Bookmark> bookmarks;

    public BookmarkManager() {
        this.bookmarks = new ArrayList<Bookmark>();
    }
    
    public void addBookmark(String name) {
        this.bookmarks.add(new Bookmark(name));
    }
    
    public List<Bookmark> getBookmarks() {
        return this.bookmarks;
    }
}
