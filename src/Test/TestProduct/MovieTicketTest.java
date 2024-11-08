package TestProduct;

import Product.MovieTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.CustomException;
import selectMovieModule.House;
import selectMovieModule.Movie;
import selectMovieModule.MovieSession;

public class MovieTicketTest {
    Movie movie;
    MovieSession movieSession;
    MovieTicket movieTicket;

    @BeforeEach
    void setUp() throws CustomException {
        movie = new Movie("test_movie", "testing", 999, 999, 10, "I", "English", "English");
        movieSession = new MovieSession(movie, "10:00", "12:00", new House());
        movieTicket = new MovieTicket(movie, movieSession, "A1");
    }

    @Test
    void testGetName() {
        Assertions.assertEquals(movieTicket.getName(), "test_movie");
    }

    @Test
    void testGetPrice() {
        Assertions.assertEquals(movieTicket.getPrice(), 999);
    }

    @Test
    void testGetMovie() {
        Assertions.assertEquals(movieTicket.getMovie(), movie);
    }

    @Test
    void testGetMovieSession() {
        Assertions.assertEquals(movieTicket.getMovieSession(), movieSession);
    }

    @Test
    void testGetSeat() {
        Assertions.assertEquals(movieTicket.getSeat(), "A1");
    }
    
    @Test
    void testSetSeat() {
        String setSeat = movieTicket.setSeat("A2");
        Assertions.assertEquals(movieTicket.getSeat(), setSeat);
    }

    @Test
void  testHashCode() {
    MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A1");
    Assertions.assertEquals(movieTicket.hashCode(), movieTicket1.hashCode());
}

    @Test
    void testEquals() {
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A1");
        Assertions.assertEquals(movieTicket, movieTicket1);
    }
    
    @Test
    void testEquals_sameObject() {
        Assertions.assertEquals(movieTicket, movieTicket);
    }
    
    @Test
    void testEquals_Null() {
        Assertions.assertNotEquals(movieTicket, null);
    }
    
    @Test
    void testEquals_differentClass() {
        Assertions.assertFalse(movieTicket.equals(movie));
    }
    
    @Test
    void testEquals_differentMovie(){
        Movie movie1 = new Movie("test_movie1", "testing", 999, 999, 10, "I", "English", "English");
        MovieTicket movieTicket1 = new MovieTicket(movie1, movieSession, "A1");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }
    
    @Test
    void testEquals_differentMovieSession() throws CustomException {
        MovieSession movieSession1 = new MovieSession(movie, "11:00", "12:00", new House());
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession1, "A1");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }
    
    @Test
    void testEquals_differentSeat() {
        MovieTicket movieTicket1 = new MovieTicket(movie, movieSession, "A2");
        Assertions.assertNotEquals(movieTicket, movieTicket1);
    }
}
