package TestShoppingCart;

import Product.Product;
import Product.Drink;
import Product.Snack;
import ShoppingCart.ShoppingCart;
import Product.MovieTicket;
import ShoppingCart.InvalidMovieTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.CustomException;
import selectMovieModule.House;
import selectMovieModule.Movie;
import selectMovieModule.MovieSession;

import java.util.*;

/**
 * ShoppingCartTest class that contains the test cases for ShoppingCart class
 */
public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    HashMap<Product, Integer> productCart;
    HashMap<Movie, Integer> movieCart;
    List<MovieTicket> movieTicketCart;
    Product testSnack;
    Product testDrink;
    Movie testMovie1;
    MovieTicket movieTicket;
    MovieSession movieSession;

    /**
     * Set up the test environment
     *
     * @throws CustomException if there is an error when creating the movie session
     */
    @BeforeEach
    void setUp() throws CustomException {
        testSnack = new Snack("snack1", 10, "100g");
        testDrink = new Drink("drink2", 20, "500ml");
        Map<Product, Integer> productMap = Map.of(
                testSnack, 1,
                testDrink, 1
        );
        testMovie1 = new Movie("movie1", "action", 123, 100, 100, "I", "English", "English");
        movieSession = new MovieSession(testMovie1, "10:00", "12:00", new House());
        movieTicket = new MovieTicket(testMovie1, movieSession, "A1");
        productCart = new HashMap<>(productMap);
        movieTicketCart = new ArrayList<>(List.of(movieTicket));
        shoppingCart = new ShoppingCart(productCart, movieTicketCart);
    }

    /**
     * Test the default constructor of ShoppingCart class
     */
    @Test
    void testDefaultConstructor() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    /**
     * Test the getter of product shopping cart
     */
    @Test
    void testGetProductShoppingCart() {
        Map<Product, Integer> actualProductCart = shoppingCart.getProductCart();
        Assertions.assertEquals(productCart, actualProductCart);
    }

    /**
     * Test addProduct method of ShoppingCart class
     */
    @Test
    void testAddProduct() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCart.addProduct(product);
        productCart.put(product, 1);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    /**
     * Test addProduct method of ShoppingCart class when the product already exists
     */
    @Test
    void testAddProduct_ProductExists() {
        shoppingCart.addProduct(testSnack);
        productCart.put(testSnack, 2);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    /**
     * Test getMovieShoppingCart method of ShoppingCart class to get the list of MovieTicket
     */
    @Test
    void testGetMovieShoppingCart() {
        List<MovieTicket> actualMovieCart = shoppingCart.getMovieTicketCart();
        Assertions.assertEquals(movieTicketCart, actualMovieCart);
    }

    /**
     * Test addMovieTicket method of ShoppingCart class
     *
     * @throws InvalidMovieTicketException if the movie ticket is invalid, which should not happen in this test
     */
    @Test
    void testAddMovieTicket() throws InvalidMovieTicketException {
        MovieTicket movieTicket = new MovieTicket(testMovie1, movieSession, "A2");
        shoppingCart.addMovieTicket(movieTicket);
        movieTicketCart.add(movieTicket);
        Assertions.assertEquals(movieTicketCart, shoppingCart.getMovieTicketCart());
    }

    /**
     * Test addMovieTicket method of ShoppingCart class when the movie ticket already exists
     * It tests whether the InvalidMovieTicketException is thrown
     */
    @Test
    void testAddMovieTicket_MovieTicketExists() {
        MovieTicket sameMovieTicket = new MovieTicket(testMovie1, movieSession, "A1");
        InvalidMovieTicketException exception = Assertions.assertThrows(InvalidMovieTicketException.class,
                () -> shoppingCart.addMovieTicket(sameMovieTicket));
        Assertions.assertEquals("Invalid movie ticket", exception.getMessage());
    }

  
    /**
     * Test addProduct method of ShoppingCart class with quantity
     */
    @Test
    void testAddProductWithQuantity() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCart.addProduct(product, 3);
        productCart.put(product, 3);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    /**
     * Test addProduct method of ShoppingCart class with quantity when the product already exists
     */
    @Test
    void testAddProductWithQuantity_ProductExists() {
        shoppingCart.addProduct(testSnack, 3);
        productCart.put(testSnack, 4);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    /**
     * Test clearAllCart method of ShoppingCart class
     */
    @Test
    void testClearAll() {
        shoppingCart.clearAllCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    /**
     * Test clearProductCart method of ShoppingCart class
     */
    @Test
    void testClearProductCart() {
        shoppingCart.clearProductCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
    }

    /**
     * Test clearMovieTicketCart method of ShoppingCart class
     */
    @Test
    void testClearMovieTicketCart() {
        shoppingCart.clearMovieTicketCart();
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    /**
     * Test removeProduct method of ShoppingCart class
     */
    @Test
    void testRemoveProduct() {
        for (int i = 0; i < 9; i++) {
            shoppingCart.addProduct(testSnack);
        }
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(testSnack, 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCart.getProductCart().get(testSnack));
    }

    /**
     * Test removeProduct method of ShoppingCart class when the product does not exist
     */
    @Test
    void testRemoveProduct_ProductNotExists() {
        Product product = new Snack("snack3", 30, "200g");
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(product, 1);
        Assertions.assertTrue(quantity.isEmpty());
    }

    /**
     * Test removeProduct method of ShoppingCart class when all products are removed
     */
    @Test
    void testRemoveProduct_NoMoreProduct() {
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(testSnack, 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCart.getProductCart().get(testSnack));
    }

    /**
     * Test removeMovieTicket method of ShoppingCart class
     */
    @Test
    void testRemoveMovieTicket() {
        Optional<MovieTicket> removeMovieTicket = shoppingCart.removeMovieTicket(movieTicket);
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
        Assertions.assertEquals(movieTicket, removeMovieTicket.orElse(null));
    }

    /**
     * Test removeMovieTicket method of ShoppingCart class when the movie ticket does not exist
     */
    @Test
    void testRemoveMovieTicket_MovieTicketNotExists() {
        MovieTicket movieTicketNotExists = new MovieTicket(testMovie1, movieSession, "A2");
        Optional<MovieTicket> removeMovieTicket = shoppingCart.removeMovieTicket(movieTicketNotExists);
        Assertions.assertTrue(removeMovieTicket.isEmpty());
    }


}
