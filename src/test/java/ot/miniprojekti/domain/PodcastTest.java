package ot.miniprojekti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PodcastTest {

    private Podcast p;

    @Before
    public void setUp() {
        this.p = new Podcast("otsikko", "nimi", "kuvaus");
    }

    @Test
    public void toStringTest() {
        String result = p.toString();
        String expected = "otsikko\n"
                + "tekijä: nimi\n"
                + "kuvaus: kuvaus\n";

        assertEquals(result, expected);
    }
}
