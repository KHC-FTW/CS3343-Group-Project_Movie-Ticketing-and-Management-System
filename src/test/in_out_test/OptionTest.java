package test.in_out_test;

//import org.junit.*;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import release.in_out.Option;

public class OptionTest {
    @Test
    public void testGetName() {
        Option option = new Option("Check Out", null);
        assertEquals("Check Out", option.getName());
    }

    @Test
    public void testGetCmdClass() {
        Option option = new Option("Check Out", null);
        assertEquals(null, option.getCmdClass());
    }

    @Test 
    public void testToString() {
        Option option = new Option("Check Out", null);
        assertEquals("Check Out", option.toString());
    }
}