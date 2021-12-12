package ot.miniprojekti.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoTests {
    
    private String foo;

    @Before
    public void setUp() {
        this.foo = "Foo";
    }
    @Test
    public void dummyDaoTest() {
        assertEquals(this.foo, "Foo");
    }

}
