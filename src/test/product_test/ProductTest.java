package test.product_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.exception.ExInvalidSeatingPlan;
import release.movie.House;
import release.movie.Movie;
import release.movie.MovieSession;
import release.product.Drink;
import release.product.MovieTicket;
import release.product.Product;
import release.product.Snack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductTest {
    Map<Product, Integer> productMap;
    Snack snack;
    Drink drink;
    MovieTicket movieTicket;// Add for testing if the product is not a subclass of ProductWithPortion

    @BeforeEach
    void setUp() throws ExInvalidSeatingPlan {
        snack = new Snack("snack1", 10, "100g");
        drink = new Drink("drink2", 20, "500ml");
        Movie movie1 = new Movie("movie1", "action", 123, 100, 100, "I", "English", "English");
        MovieSession movieSession = new MovieSession(movie1, "10:00", "12:00", new House());
        movieTicket = new MovieTicket(movie1, movieSession, "A1");
        productMap = new LinkedHashMap<>();
        productMap.put(snack, 1);
        productMap.put(drink, 1);
        productMap.put(movieTicket, 1);
    }

    @Test
    void testFormatProductMap() {
        final int lineSeparator = 73;
        String snackTitle = String.format("\n%4s%-32s%-16s%-15s%-6s\n%s\n", " ", "Snacks/Drinks",
                "Portion", "Quantity", "Price", "-".repeat(lineSeparator));
        String formattedSnack = String.format("%2d) %-30s%2s%-16s%5d%10s$%-4.1f\n", 1, snack.getName(), " ",
                snack.getPortion(), 1, " ",
                snack.getPrice());
        String formattedDrink = String.format("%2d) %-30s%2s%-16s%5d%10s$%-4.1f\n", 2, drink.getName(),
                " ", drink.getPortion(), 1, " ", (drink).getPrice());
        String formattedMovieTicket = String.format("%2d) %-30s%2s%-16s%5d%10s$%-4.1f\n", 3, movieTicket.getName(),
                " ", "N/A", 1, " ", movieTicket.getPrice());
        String expected = snackTitle + formattedSnack + formattedDrink + formattedMovieTicket + "-".repeat(
                lineSeparator) + "\n";
        Assertions.assertEquals(expected, Product.formatProductMap(productMap));
    }

    @Test
    void testFormatProductMapEmpty() {
        Assertions.assertEquals("", Product.formatProductMap(new HashMap<>()));
    }

    @Test
    void testFormatProductMapNull() {
        Assertions.assertEquals("", Product.formatProductMap(null));
    }
}
