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

    @Test
    void testDefaultConstructor() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    @Test
    void testGetProductShoppingCart() {
        Map<Product, Integer> actualProductCart = shoppingCart.getProductCart();
        Assertions.assertEquals(productCart, actualProductCart);
    }

    @Test
    void testAddProduct() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCart.addProduct(product);
        productCart.put(product, 1);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    @Test
    void testAddProduct_ProductExists() {
        shoppingCart.addProduct(testSnack);
        productCart.put(testSnack, 2);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    @Test
    void testGetMovieShoppingCart() {
        List<MovieTicket> actualMovieCart = shoppingCart.getMovieTicketCart();
        Assertions.assertEquals(movieTicketCart, actualMovieCart);
    }

    @Test
    void testAddMovieTicket() throws InvalidMovieTicketException {
        MovieTicket movieTicket = new MovieTicket(testMovie1, movieSession, "A2");
        shoppingCart.addMovieTicket(movieTicket);
        movieTicketCart.add(movieTicket);
        Assertions.assertEquals(movieTicketCart, shoppingCart.getMovieTicketCart());
    }

    @Test
    void testAddMovieTicket_MovieTicketExists() {
        MovieTicket sameMovieTicket = new MovieTicket(testMovie1, movieSession, "A1");
        InvalidMovieTicketException exception = Assertions.assertThrows(InvalidMovieTicketException.class,
                () -> shoppingCart.addMovieTicket(sameMovieTicket));
        Assertions.assertEquals("Invalid movie ticket", exception.getMessage());
    }

    @Test
    void testAddProductWithQuantity() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCart.addProduct(product, 3);
        productCart.put(product, 3);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    @Test
    void testAddProductWithQuantity_ProductExists() {
        shoppingCart.addProduct(testSnack, 3);
        productCart.put(testSnack, 4);
        Assertions.assertEquals(productCart, shoppingCart.getProductCart());
    }

    @Test
    void testClearAll() {
        shoppingCart.clearAllCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    @Test
    void testClearProductCart() {
        shoppingCart.clearProductCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
    }

    @Test
    void testClearMovieTicketCart() {
        shoppingCart.clearMovieTicketCart();
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    @Test
    void testRemoveProduct() {
        for (int i = 0; i < 9; i++) {
            shoppingCart.addProduct(testSnack);
        }
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(testSnack, 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCart.getProductCart().get(testSnack));
    }

    @Test
    void testRemoveProduct_ProductNotExists() {
        Product product = new Snack("snack3", 30, "200g");
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(product, 1);
        Assertions.assertTrue(quantity.isEmpty());
    }

    @Test
    void testRemoveProduct_NoMoreProduct() {
        Optional<Integer> quantity = shoppingCart.removeFromProductCart(testSnack, 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCart.getProductCart().get(testSnack));
    }

    @Test
    void testRemoveMovieTicket() {
//        shoppingCart.addMovieTicket(movieTicket);
        Optional<MovieTicket> removeMovieTicket = shoppingCart.removeMovieTicket(movieTicket);
        Assertions.assertEquals(new ArrayList<>(), shoppingCart.getMovieTicketCart());
    }

    @Test
    void testRemoveMovieTicket_MovieTicketNotExists() {
        MovieTicket movieTicketNotExists = new MovieTicket(testMovie1, movieSession, "A2");
        Optional<MovieTicket> removeMovieTicket = shoppingCart.removeMovieTicket(movieTicketNotExists);
        Assertions.assertTrue(removeMovieTicket.isEmpty());
    }


}
