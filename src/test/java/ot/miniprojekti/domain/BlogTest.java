package ot.miniprojekti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlogTest {

    private Blog b;

    @Before
    public void setUp() {
        this.b = new Blog(0, "otsikko", "kirjoittaja", "url");
    }

    @Test
    public void toStringTest() {
        String result = b.toString();
        String expected = "1. otsikko\n"
                + "tekijä: kirjoittaja\n"
                + "url: url\n"
                + "";

        assertEquals(result, expected);
    }
}
