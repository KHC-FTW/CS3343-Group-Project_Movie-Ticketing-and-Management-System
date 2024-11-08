package TestProduct;

import Product.Drink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test for Drink class in package Product
 */
public class DrinkTest {
    private final List<Drink> testdrinkList = new ArrayList<>();

    /**
     * Set up the test drink list
     */
    @BeforeEach
    void setup() {
        testdrinkList.add(new Drink("Drink 1", 10, "10ml"));
        testdrinkList.add(new Drink("Drink 2", 20, "20L"));
    }

    /**
     * Clear the test drink list after each test
     */
    @AfterEach
    void clear() {
        testdrinkList.clear();
    }

    /**
     * Test get all drinks
     */
    @Test
    void testGetALl() {
        List<Drink> drinkList = Drink.getALlDrinks();
        for (int i = 0; i < testdrinkList.size(); i++) {
            Assertions.assertEquals(testdrinkList.get(i), drinkList.get(i));
        }

    }

    /**
     * Test get name of the drink
     */
    @Test
    void testGetName() {
        String name = testdrinkList.getFirst().getName();
        Assertions.assertEquals(name, "Drink 1");
    }

    /**
     * Test set name of the drink
     */
    @Test
    void testGetPortion() {
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals(portion, "10ml");
    }

    /**
     * Test set portion of the drink
     */
    @Test
    void testSetPortion() {
        testdrinkList.getFirst().setPortion("20ml");
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals(portion, "20ml");
    }

    /**
     * Test get price of the drink
     */
    @Test
    void testGetPrice() {
        double price = testdrinkList.getFirst().getPrice();
        Assertions.assertEquals(price, 10);
    }
    

    /**
     * Test search drink
     */
    @Test
    void testSearchDrink() {
        Drink drink = testdrinkList.getFirst();
        Optional<Drink> drinkOptional = Drink.searchDrink("Drink 1");
        Assertions.assertTrue(drinkOptional.isPresent());
        Assertions.assertEquals(drink, drinkOptional.get());
    }

    /**
     * Test search drink that does not exist
     */
    @Test
    void testSearchDrinkNotFound() {
        Optional<Drink> drinkOptional = Drink.searchDrink("Drink not exist");
        Assertions.assertTrue(drinkOptional.isEmpty());
    }

    /**
     * Test equals method
     */
    @Test
    void testEquals() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "10ml");
        Assertions.assertTrue(drink1.equals(drink2));
        Assertions.assertEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different name of drink
     */
    @Test
    void testEquals_DifferentName() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 2", 10, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different price of drink
     */
    @Test
    void testEquals_DifferentPrice() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 20, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different portion of drink
     */
    @Test
    void testEquals_DifferentPortion() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "20ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with null
     */
    @Test
    void testEquals_Null() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertFalse(drink1.equals(null));
        Assertions.assertNotEquals(drink1.hashCode(), 0);
    }

    /**
     * Test equals method with different class
     */
    @Test
    void testEquals_DifferentClass() {
        Drink drink1 = testdrinkList.getFirst();
        Object obj = new Object();
        Assertions.assertFalse(drink1.equals(obj));
        Assertions.assertNotEquals(drink1.hashCode(), obj.hashCode());
    }

    /**
     * Test equals method on the same instance of drink
     */
    @Test
    void testEquals_DrinkEqualsItself() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertTrue(drink1.equals(drink1));
    }

}

