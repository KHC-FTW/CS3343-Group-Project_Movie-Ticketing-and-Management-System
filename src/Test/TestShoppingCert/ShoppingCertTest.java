package TestShoppingCert;

import Product.Product;
import Product.Drink;
import Product.Snack;
import ShoppingCert.ShoppingCert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoppingCertTest {
    ShoppingCert shoppingCert;
    HashMap<Product, Integer> productCert;
    HashMap<Movie, Integer> movieCert;
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
        productCert = new HashMap<>(productMap);
        movieCert = new HashMap<>(movieMap);
        shoppingCert = new ShoppingCert(productCert, movieCert);
    }

    @Test
    void testDefaultConstructor() {
        ShoppingCert shoppingCert = new ShoppingCert();
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getProductCert());
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getMovieCert());
    }

    @Test
    void testGetProductShoppingCert() {
        Map<Product, Integer> actualProductCert = shoppingCert.getProductCert();
        Assertions.assertEquals(productCert, actualProductCert);
    }

    @Test
    void testAddProduct() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCert.addProduct(product);
        productCert.put(product, 1);
        Assertions.assertEquals(productCert, shoppingCert.getProductCert());
    }

    @Test
    void testAddProduct_ProductExists() {
        shoppingCert.addProduct(testSnack);
        productCert.put(testSnack, 2);
        Assertions.assertEquals(productCert, shoppingCert.getProductCert());
    }

    @Test
    void testGetMovieShoppingCert() {
        Map<Movie, Integer> actualMovieCert = shoppingCert.getMovieCert();
        Assertions.assertEquals(movieCert, actualMovieCert);
    }

    @Test
    void testAddMovie() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        shoppingCert.addMovie(movie);
        movieCert.put(movie, 1);
        Assertions.assertEquals(movieCert, shoppingCert.getMovieCert());
    }

    @Test
    void testAddMovie_MovieExists() {
        shoppingCert.addMovie(testMovie1);
        movieCert.put(testMovie1, 2);
        Assertions.assertEquals(movieCert, shoppingCert.getMovieCert());
    }

    @Test
    void testAddProductWithQuantity() {
        Product product = new Snack("snack3", 30, "200g");
        shoppingCert.addProduct(product, 3);
        productCert.put(product, 3);
        Assertions.assertEquals(productCert, shoppingCert.getProductCert());
    }

    @Test
    void testAddProductWithQuantity_ProductExists() {
        shoppingCert.addProduct(testSnack, 3);
        productCert.put(testSnack, 4);
        Assertions.assertEquals(productCert, shoppingCert.getProductCert());
    }

    @Test
    void testAddMovieWithQuantity() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        shoppingCert.addMovie(movie, 3);
        movieCert.put(movie, 3);
        Assertions.assertEquals(movieCert, shoppingCert.getMovieCert());
    }

    @Test
    void testAddMovieWithQuantity_MovieExists() {
        shoppingCert.addMovie(testMovie1, 3);
        movieCert.put(testMovie1, 4);
        Assertions.assertEquals(movieCert, shoppingCert.getMovieCert());
    }

    @Test
    void testClearAll() {
        shoppingCert.clearAllCert();
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getProductCert());
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getMovieCert());
    }

    @Test
    void testClearProductCert() {
        shoppingCert.clearProductCert();
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getProductCert());
    }

    @Test
    void testClearMovieCert() {
        shoppingCert.clearMovieCert();
        Assertions.assertEquals(new HashMap<>(), shoppingCert.getMovieCert());
    }

    @Test
    void testRemoveProduct() {
        for (int i = 0; i < 9; i++) {
            shoppingCert.addProduct(testSnack);
        }
        Optional<Integer> quantity = shoppingCert.removeCert(testSnack, shoppingCert.getProductCert(), 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCert.getProductCert().get(testSnack));
    }

    @Test
    void testRemoveProduct_ProductNotExists() {
        Product product = new Snack("snack3", 30, "200g");
        Optional<Integer> quantity = shoppingCert.removeCert(product, shoppingCert.getProductCert(), 1);
        Assertions.assertTrue(quantity.isEmpty());
    }

    @Test
    void testRemoveProduct_NoMoreProduct() {
        Optional<Integer> quantity = shoppingCert.removeCert(testSnack, shoppingCert.getProductCert(), 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCert.getProductCert().get(testSnack));
    }
    
    @Test
    void testRemoveMovie() {
        for (int i = 0; i < 9; i++) {
            shoppingCert.addMovie(testMovie1);
        }
        Optional<Integer> quantity = shoppingCert.removeCert(testMovie1, shoppingCert.getMovieCert(), 9);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(1, shoppingCert.getMovieCert().get(testMovie1));
    }
    
    @Test
    void testRemoveMovie_MovieNotExists() {
        Movie movie = new Movie("movie3", "comedy", 100, 120, "IIB", "Chinese", "Chinese");
        Optional<Integer> quantity = shoppingCert.removeCert(movie, shoppingCert.getMovieCert(), 1);
        Assertions.assertTrue(quantity.isEmpty());
    }
    
    @Test
    void testRemoveMovie_NoMoreMovie() {
        Optional<Integer> quantity = shoppingCert.removeCert(testMovie1, shoppingCert.getMovieCert(), 1);
        Assertions.assertTrue(quantity.isPresent());
        Assertions.assertEquals(null, shoppingCert.getMovieCert().get(testMovie1));
    }

}
