package TestShoppingCart;

import Product.Product;
import Product.Drink;
import Product.Snack;
import ShoppingCart.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    HashMap<Product, Integer> productCart;
    HashMap<Movie, Integer> movieCart;
    Product testSnack;
    Product testDrink;
    Movie testMovie1;
    Movie testMovie2;

    @BeforeEach
    void setUp() {
        testSnack = new Snack("snack1", 10, "100g");
        testDrink = new Drink("drink2", 20, "500ml");
        Map<Product, Integer> productMap = Map.of(
                testSnack, 1,
                testDrink, 1
        );
        testMovie1 = new Movie("movie1", "action", 123, 100, "I", "English", "English");
        testMovie2 = new Movie("movie2", "horror", 111, 123, "IIA", "Chinese", "Chinese");
        Map<Movie, Integer> movieMap = Map.of(testMovie1, 1, testMovie2, 1);
        productCart = new HashMap<>(productMap);
        movieCart = new HashMap<>(movieMap);
        shoppingCart = new ShoppingCart(productCart, movieCart);
    }

    @Test
    void testDefaultConstructor() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getMovieCart());
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
        Map<Movie, Integer> actualMovieCart = shoppingCart.getMovieCart();
        Assertions.assertEquals(movieCart, actualMovieCart);
    }

    @Test
    void testAddMovie() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        shoppingCart.addMovie(movie);
        movieCart.put(movie, 1);
        Assertions.assertEquals(movieCart, shoppingCart.getMovieCart());
    }

    @Test
    void testAddMovie_MovieExists() {
        shoppingCart.addMovie(testMovie1);
        movieCart.put(testMovie1, 2);
        Assertions.assertEquals(movieCart, shoppingCart.getMovieCart());
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
    void testAddMovieWithQuantity() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        shoppingCart.addMovie(movie, 3);
        movieCart.put(movie, 3);
        Assertions.assertEquals(movieCart, shoppingCart.getMovieCart());
    }

    @Test
    void testAddMovieWithQuantity_MovieExists() {
        shoppingCart.addMovie(testMovie1, 3);
        movieCart.put(testMovie1, 4);
        Assertions.assertEquals(movieCart, shoppingCart.getMovieCart());
    }

    @Test
    void testClearAll() {
        shoppingCart.clearAllCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getMovieCart());
    }

    @Test
    void testClearProductCart() {
        shoppingCart.clearProductCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getProductCart());
    }

    @Test
    void testClearMovieCart() {
        shoppingCart.clearMovieCart();
        Assertions.assertEquals(new HashMap<>(), shoppingCart.getMovieCart());
    }

    @Test
    void testRemoveProduct() {
        for (int i = 0; i < 9; i++) {
            shoppingCart.addProduct(testSnack);
        }
        Optional<Integer> quantity = shoppingCart.removeCart(testSnack, shoppingCart.getProductCart(), 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCart.getProductCart().get(testSnack));
    }

    @Test
    void testRemoveProduct_ProductNotExists() {
        Product product = new Snack("snack3", 30, "200g");
        Optional<Integer> quantity = shoppingCart.removeCart(product, shoppingCart.getProductCart(), 1);
        Assertions.assertTrue(quantity.isEmpty());
    }

    @Test
    void testRemoveProduct_NoMoreProduct() {
        Optional<Integer> quantity = shoppingCart.removeCart(testSnack, shoppingCart.getProductCart(), 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCart.getProductCart().get(testSnack));
    }
    
    @Test
    void testRemoveMovie() {
        for (int i = 0; i < 9; i++) {
            shoppingCart.addMovie(testMovie1);
        }
        Optional<Integer> quantity = shoppingCart.removeCart(testMovie1, shoppingCart.getMovieCart(), 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCart.getMovieCart().get(testMovie1));
    }
    
    @Test
    void testRemoveMovie_MovieNotExists() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        Optional<Integer> quantity = shoppingCart.removeCart(movie, shoppingCart.getMovieCart(), 1);
        Assertions.assertTrue(quantity.isEmpty());
    }
    
    @Test
    void testRemoveMovie_NoMoreMovie() {
        Optional<Integer> quantity = shoppingCart.removeCart(testMovie1, shoppingCart.getMovieCart(), 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCart.getMovieCart().get(testMovie1));
        
    }
    
}
