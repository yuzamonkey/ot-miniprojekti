package ot.miniprojekti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DomainTests {

    private Blog bl;
    private Video v;
    private Book bo;
    private Podcast p;

    @Before
    public void setUp() {
        this.bl = new Blog(0, "otsikko", "kirjoittaja", "url");
        this.p = new Podcast(0, "otsikko", "nimi", "kuvaus");
        this.v = new Video(0, "otsikko", "url", "kommentti");
        this.bo = new Book(0, "kirjoittaja", "otsikko", "0000");
    }

    @Test
    public void blogToStringTest() {
        String result = bl.toString();
        String expected = "otsikko\n"
                + "tekijä: kirjoittaja\n"
                + "url: url\n"
                + "id: 0\n"
                + "";

        assertEquals(result, expected);
    }

    @Test
    public void videoToStringTest() {
        String result = v.toString();
        String expected = "otsikko\n"
                + "url: url\n"
                + "kommentti: kommentti\n"
                + "id: 0"
                + "";

        assertEquals(result, expected);
    }

    @Test
    public void bookToStringTest() {
        String result = bo.toString();
        String expected = "otsikko\n"
                + "tekijä: kirjoittaja\n"
                + "isbn: 0000\n"
                + "id: 0"
                + "";

        assertEquals(result, expected);
    }

    @Test
    public void podcastToStringTest() {
        String result = p.toString();
        String expected = "otsikko\n"
                + "tekijä: nimi\n"
                + "kuvaus: kuvaus\n"
                + "id: 0";

        assertEquals(result, expected);
    }
}
