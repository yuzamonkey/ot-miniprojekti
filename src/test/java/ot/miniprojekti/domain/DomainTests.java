package ot.miniprojekti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DomainTests {

    private Blog bl;
    private Book bo;
    private Podcast p;
    private Video v;
    private Blog blr;
    private Book bor;
    private Podcast pr;
    private Video vr;

    @Before
    public void setUp() {
        this.bl = new Blog(1, "otsikko", "kirjoittaja", "url", false);
        this.bo = new Book(2, "kirjoittaja", "otsikko", "0000", false);
        this.p = new Podcast(3, "otsikko", "nimi", "kuvaus", false);
        this.v = new Video(4, "otsikko", "url", "kommentti", false);

        this.blr = new Blog(1, "otsikko", "kirjoittaja", "url", true);
        this.blr.setNote("muistiinpano");
        this.bor = new Book(2, "kirjoittaja", "otsikko", "0000", true);
        this.bor.setNote("muistiinpano");
        this.pr = new Podcast(3, "otsikko", "nimi", "kuvaus", true);
        this.pr.setNote("muistiinpano");
        this.vr = new Video(4, "otsikko", "url", "kommentti", true);
        this.vr.setNote("muistiinpano");
    }

    @Test
    public void blogToStringTest() {
        String result = bl.toString();
        String expected = "[1] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "URL: url";

        assertEquals(result, expected);

        result = blr.toString();
        expected = "[1] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "URL: url\n"
                + "Muistiinpano: muistiinpano";

        assertEquals(result, expected);

        blr.setNote(null);
        result = blr.toString();
        expected = "[1] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "URL: url\n"
                + "Muistiinpano: -";

        assertEquals(result, expected);
    }

    @Test
    public void bookToStringTest() {
        String result = bo.toString();
        String expected = "[2] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "ISBN: 0000";

        assertEquals(result, expected);

        result = bor.toString();
        expected = "[2] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "ISBN: 0000\n"
                + "Muistiinpano: muistiinpano";

        assertEquals(result, expected);

        bor.setNote(null);
        result = bor.toString();
        expected = "[2] otsikko\n"
                + "Tekijä: kirjoittaja\n"
                + "ISBN: 0000\n"
                + "Muistiinpano: -";

        assertEquals(result, expected);

    }

    @Test
    public void podcastToStringTest() {
        String result = p.toString();
        String expected = "[3] otsikko\n"
                + "Tekijä: nimi\n"
                + "Kuvaus: kuvaus";

        assertEquals(result, expected);

        result = pr.toString();
        expected = "[3] otsikko\n"
                + "Tekijä: nimi\n"
                + "Kuvaus: kuvaus\n"
                + "Muistiinpano: muistiinpano";

        assertEquals(result, expected);

        pr.setNote(null);
        result = pr.toString();
        expected = "[3] otsikko\n"
                + "Tekijä: nimi\n"
                + "Kuvaus: kuvaus\n"
                + "Muistiinpano: -";

        assertEquals(result, expected);
    }

    @Test
    public void videoToStringTest() {
        String result = v.toString();
        String expected = "[4] otsikko\n"
                + "URL: url\n"
                + "Kommentti: kommentti";

        assertEquals(result, expected);

        result = vr.toString();
        expected = "[4] otsikko\n"
                + "URL: url\n"
                + "Kommentti: kommentti\n"
                + "Muistiinpano: muistiinpano";

        assertEquals(result, expected);

        vr.setNote(null);
        result = vr.toString();
        expected = "[4] otsikko\n"
                + "URL: url\n"
                + "Kommentti: kommentti\n"
                + "Muistiinpano: -";

        assertEquals(result, expected);
    }
}
