package ot.miniprojekti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VideoTest {

    private Video v;

    @Before
    public void setUp() {
        this.v = new Video(0, "otsikko", "url", "kommentti");
    }

    @Test
    public void toStringTest() {
        String result = v.toString();
        String expected = "1. otsikko\n"
                + "url: url\n"
                + "kommentti: kommentti\n"
                + "";

        assertEquals(result, expected);
    }
}
