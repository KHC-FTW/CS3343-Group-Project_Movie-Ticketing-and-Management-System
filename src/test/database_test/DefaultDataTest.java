package test.database_test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.database.DefaultData;
import release.database.Database;
import release.movie.Movie;
import release.product.ProductWithPortion;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDataTest {

    private DefaultData defaultData;
    private Database db;

    @BeforeEach
    public void setUp() {
        defaultData = DefaultData.getInstance();
        db = Database.getInstance();
        db.resetDB();
    }


    @Test
    public void testSetUpDB() {
        defaultData.setUpDB();
        assertEquals("10:00", db.getOpenHours());
        assertEquals("24:00", db.getCloseHours());
        assertEquals(5, db.getHouses().size());
        assertEquals(6, db.getProducts().size());
    }

    @Test
    public void testLoadDefaultMoviesToDB() {
        List<Movie> movies = defaultData.loadDefaultMoviesToDB();
        assertEquals(14, movies.size());
        assertTrue(defaultData.getMoviesLoadedStatus());
        assertEquals(14, db.getMovies().size());
    }

    @Test
    public void testGetDefaultMovies() {
        List<Movie> movies = defaultData.getDefaultMovies();
        assertEquals(14, movies.size());
    }

    @Test
    public void testGetMoviesLoadedStatus() {
        defaultData.loadDefaultMoviesToDB();
        assertTrue(defaultData.getMoviesLoadedStatus());
    }
}
