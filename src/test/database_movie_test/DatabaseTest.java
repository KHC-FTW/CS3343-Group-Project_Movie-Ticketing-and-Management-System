package test.database_movie_test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.database.Database;
import release.exception.ExInvalidSeatingPlan;
import release.movie.House;
import release.movie.Movie;
import release.product.Drink;
import release.product.ProductWithPortion;
import release.product.Snack;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database db;

    @BeforeEach
    public void setUp() {
        db = Database.getInstance();
        db.resetDB();
    }

    @Test
    public void testSetOpeningHours() {
        db.setOpeningHours("09:00");
        assertEquals("09:00", db.getOpenHours());
    }

    @Test
    public void testSetClosingHours() {
        db.setClosingHours("23:00");
        assertEquals("23:00", db.getCloseHours());
    }

    @Test
    public void testSetHouses() throws ExInvalidSeatingPlan {
        List<House> houses = new ArrayList<>(List.of(new House(1, 20, 20), new House(2, 15, 15)));
        db.setHouses(houses);
        assertEquals(houses, db.getHouses());
        assertEquals(2, db.getHouses().size());
    }

    @Test
    public void testSetMovies() {
        List<Movie> movies = new ArrayList<>(List.of(new Movie("Test Movie", "Action", 120, 50, 8.0, "IIB", "English", "Chinese")));
        db.setMovies(movies);
        assertEquals(movies, db.getMovies());
        assertEquals(1, db.getMovies().size());
    }

    @Test
    public void testSetProducts() {
        List<ProductWithPortion> products = new ArrayList<>(List.of(new Snack("Test Product", 10.0, "Small"), new Drink("Test Product 2", 15.0, "Large")));
        db.setProducts(products);
        assertEquals(products, db.getProducts());
        assertEquals(2, db.getProducts().size());
    }

    @Test
    public void testAddMovie() {
        Movie movie = new Movie("Test Movie", "Action", 120, 50, 8.0, "IIB", "English", "Chinese");
        db.addMovie(movie);
        assertTrue(db.getMovies().contains(movie));
    }

    @Test
    public void testAddProduct() {
        ProductWithPortion product = new Snack("Test Product", 10.0, "Small");
        db.addProduct(product);
        assertTrue(db.getProducts().contains(product));
    }

    @Test
    public void testResetDB() throws ExInvalidSeatingPlan {
        db.setOpeningHours("09:00");
        db.setClosingHours("23:00");
        db.setHouses(new ArrayList<>(List.of(new House(1, 20, 20))));
        db.setMovies(new ArrayList<>(List.of(new Movie("Test Movie", "Action", 120, 50, 8.0, "IIB", "English", "Chinese"))));
        db.setProducts(new ArrayList<>(List.of(new Drink("Test Product", 10.0, "Small"))));
        db.resetDB();
        assertNull(db.getOpenHours());
        assertNull(db.getCloseHours());
        assertTrue(db.getHouses().isEmpty());
        assertTrue(db.getMovies().isEmpty());
        assertTrue(db.getProducts().isEmpty());
    }
}
