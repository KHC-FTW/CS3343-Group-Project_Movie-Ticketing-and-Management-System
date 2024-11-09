package TestProduct;

import Product.MovieTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.CustomException;
import selectMovieModule.House;
import selectMovieModule.Movie;
import selectMovieModule.MovieSession;

/**
 * MovieTicketTest class<br>
 * It is used to test the MovieTicket class
 */
public class MovieTicketTest {
    Movie movie;
    MovieSession movieSession;
    MovieTicket movieTicket;

    /**
     * Set up the test environment<br>
     * Initialize the Movie, MovieSession and MovieTicket objects
     */
    @BeforeEach
    void setUp() throws CustomException {
        movie = new Movie("test_movie", "testing", 999, 999, 10, "I", "English", "English");
        movieSession = new MovieSession(movie, "10:00", "12:00", new House());
        movieTicket = new MovieTicket(movie, movieSession, "A1");
    }

    /**
     * Test the getName method<br>
     * Check if the name of the movie ticket is "test_movie"
     */
    @Test
    void testGetName() {
        Assertions.assertEquals(movieTicket.getName(), "test_movie");
    }

    /**
     * Test the getPrice method<br>
     * Check if the price of the movie ticket is 999
     */
    @Test
    void testGetPrice() {
        Assertions.assertEquals(movieTicket.getPrice(), 999);
    }

    /**
     * Test the getMovie method<br>
     * Check if the movie of the movie ticket is the same as the movie object
     */
    @Test
    void testGetMovie() {
        Assertions.assertEquals(movieTicket.getMovie(), movie);
    }

    /**
     * Test the getMovieSession method<br>
     * Check if the movie session of the movie ticket is the same as the movie session object
     */
    @Test
    void testGetMovieSession() {
        Assertions.assertEquals(movieTicket.getMovieSession(), movieSession);
    }

    /**
     * Test the getSeat method<br>
     * Check if the seat of the movie ticket is "A1"
     */
    @Test
    void testGetSeat() {
        Assertions.assertEquals(movieTicket.getSeat(), "A1");
    }

    /**
     * Test the setSeat method<br>
     * Check if the seat of the movie ticket is set to "A2"
     */
    @Test
    void testSetSeat() {
        String setSeat = movieTicket.setSeat("A2");
        Assertions.assertEquals(movieTicket.getSeat(), setSeat);
    }

    /**
     * Test the hashCode method<br>
     * Check if the hash code of the movie ticket is the same as the hash code of another movie ticket object
     */
    @Test
    void testHashCode() {
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A1");
        Assertions.assertEquals(movieTicket.hashCode(), movieTicket1.hashCode());
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is equal to another movie ticket object
     */
    @Test
    void testEquals() {
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A1");
        Assertions.assertEquals(movieTicket, movieTicket1);
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to another movie ticket object
     */
    @Test
    void testEquals_sameObject() {
        Assertions.assertEquals(movieTicket, movieTicket);
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to null
     */
    @Test
    void testEquals_Null() {
        Assertions.assertNotEquals(movieTicket, null);
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to a different class object
     */
    @Test
    void testEquals_differentClass() {
        Assertions.assertFalse(movieTicket.equals(movie));
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to a different movie object
     */
    @Test
    void testEquals_differentMovie() {
        Movie movie1 = new Movie("test_movie1", "testing", 999, 999, 10, "I", "English", "English");
        MovieTicket movieTicket1 = new MovieTicket(movie1, movieSession, "A1");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to a different movie session object
     */
    @Test
    void testEquals_differentMovieSession() throws CustomException {
        MovieSession movieSession1 = new MovieSession(movie, "11:00", "12:00", new House());
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession1, "A1");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }

    /**
     * Test the equals method<br>
     * Check if the movie ticket is not equal to a different seat
     */
    @Test
    void testEquals_differentSeat() {
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A2");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }
}
