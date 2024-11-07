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

}
