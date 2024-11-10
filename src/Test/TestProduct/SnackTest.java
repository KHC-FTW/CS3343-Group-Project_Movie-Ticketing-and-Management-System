package TestProduct;

import Product.Snack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
 * Test for Snack class in package Product
 */
public class SnackTest {
    Snack snack1;
    Snack snack2;

    /**
     * Set up the test snacks before each test
     */
    @BeforeEach
    public void setUp() {
        snack1 = new Snack("fist snack", 10, "10g");
        snack2 = new Snack("second snack", 20, "20g");
    }

    /**
     * Test equals method with the same instance of snack
     */
    @Test
    public void testEquals_SameSnacks() {
        Assertions.assertTrue(snack1.equals(snack1));
        Assertions.assertEquals(snack1.hashCode(), snack1.hashCode());
    }

    /**
     * Test equals method with two different snacks
     */
    @Test
    public void testEquals_TwoDifferentSnacks() {
        Assertions.assertFalse(snack1.equals(snack2));
        Assertions.assertNotEquals(snack1.hashCode(), snack2.hashCode());
    }

    /**
     * Test equals method with two different snacks with the same name
     */
    @Test
    public void testEquals_TwoDifferentSnacksWithSameName() {
        Snack snack3 = new Snack("fist snack", 20, "20g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    /**
     * Test equals method with two different snacks with the same price
     */
    @Test
    public void testEquals_TwoDifferentSnacksWithSamePrice() {
        Snack snack3 = new Snack("second snack", 10, "20g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    /**
     * Test equals method with two different snacks with the same portion
     */
    @Test
    public void testEquals_TwoDifferentSnacksWithSamePortion() {
        Snack snack3 = new Snack("second snack", 20, "10g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    /**
     * Test equals method with null
     */
    @Test
    public void testEquals_Null() {
        Assertions.assertFalse(snack1.equals(null));
        Assertions.assertNotEquals(snack1.hashCode(), 0);
    }
    
    /**
     * Test equals method with different class
     */
    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        Assertions.assertFalse(snack1.equals(obj));
        Assertions.assertNotEquals(snack1.hashCode(), obj.hashCode());
    }

    /**
     * Test getName method
     */
    @Test
    public void testGetName() {
        Assertions.assertEquals("fist snack", snack1.getName());
    }

    /**
     * Test getPortion method
     */
    @Test
    public void testGetPortion() {
        Assertions.assertEquals("10g", snack1.getPortion());
    }

    /**
     * Test setPortion method
     */
    @Test
    public void testSetPortion() {
        String portionSet = snack1.setPortion("20g");
        Assertions.assertEquals("20g", portionSet);
    }

    /**
     * Test getPrice method
     */
    @Test
    public void testGetPrice() {
        Assertions.assertEquals(10, snack1.getPrice());
    }
    

    /**
     * Test getAllSnacks method
     */
    @Test
    public void testGetAll() {
        List<Snack> allSnacks = Snack.getAllSnacks();
        Assertions.assertEquals(allSnacks.getFirst(), snack1);
        Assertions.assertEquals(allSnacks.get(1), snack2);
    }
    
    /**
     * Test searchProduct method
     */
    @Test
    public void testSearchSnack() {
        Optional<Snack> snackOptional = Snack.searchSnack("fist snack");
        Assertions.assertTrue(snackOptional.isPresent());
        Assertions.assertEquals(snack1, snackOptional.get());
    }
    
    /**
     * Test searchSnack method with snack that does not exist
     */
    @Test
    public void testSearchSnackNotFound() {
        Optional<Snack> snackOptional = Snack.searchSnack("snack not exist");
        Assertions.assertTrue(snackOptional.isEmpty());
    }
}
