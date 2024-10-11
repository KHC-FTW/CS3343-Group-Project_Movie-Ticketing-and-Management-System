package TestProduct;

import Product.Drink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DrinkTest {
    private final List<Drink> testdrinkList = new ArrayList<>();

    @BeforeEach
    void setup() {
        testdrinkList.add(new Drink("Drink 1", 10, "10ml"));
        testdrinkList.add(new Drink("Drink 2", 20, "20L"));
    }

    @AfterEach
    void clear() {
        testdrinkList.clear();
    }

    @Test
    void testGetALl() {
        List<Drink> drinkList = Drink.getALlDrinks();
        for (int i = 0; i < testdrinkList.size(); i++) {
            Assertions.assertEquals(testdrinkList.get(i), drinkList.get(i));
        }

    }

    @Test
    void testGetName() {
        String name = testdrinkList.getFirst().getName();
        Assertions.assertEquals(name, "Drink 1");
    }

    @Test
    void testGetPortion() {
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals(portion, "10ml");
    }

    @Test
    void testSetPortion() {
        testdrinkList.getFirst().setPortion("20ml");
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals(portion, "20ml");
    }

    @Test
    void testGetPrice() {
        double price = testdrinkList.getFirst().getPrice();
        Assertions.assertEquals(price, 10);
    }

    @Test
    void testSetPrice() {
        testdrinkList.getFirst().setPrice(20);
        double price = testdrinkList.getFirst().getPrice();
        Assertions.assertEquals(price, 20);
    }
    
    @Test
    void testEquals() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "10ml");
        Assertions.assertTrue(drink1.equals(drink2));
        Assertions.assertEquals(drink1.hashCode(), drink2.hashCode());
    }
    
    @Test
    void testEqualsDifferentName() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 2", 10, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }
    
    @Test
    void testEqualsDifferentPrice() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 20, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }
    
    @Test
    void testEqualsDifferentPortion() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "20ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }
    
    @Test
    void testEqualsNull() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertFalse(drink1.equals(null));
        Assertions.assertNotEquals(drink1.hashCode(), 0);
    }
    
    @Test
    void testEqualsDifferentClass() {
        Drink drink1 = testdrinkList.getFirst();
        Object obj = new Object();
        Assertions.assertFalse(drink1.equals(obj));
        Assertions.assertNotEquals(drink1.hashCode(), obj.hashCode());
    }
    
    @Test
    void testEqualsDrinkEqualsItself() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertTrue(drink1.equals(drink1));
    }

}
