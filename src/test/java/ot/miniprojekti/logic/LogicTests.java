package ot.miniprojekti.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTests {
    private String bar;

    @Before
    public void setUp() {
        this.bar = "bar";
    }

    @Test
    public void dummyLogicTest() {
        assertEquals(this.bar, "bar");
    }

}
