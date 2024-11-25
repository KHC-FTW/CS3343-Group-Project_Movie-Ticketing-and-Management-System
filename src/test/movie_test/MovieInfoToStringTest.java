package test.movie_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.exception.ExInvalidSeatingPlan;
import release.exception.ExNoMovieToShow;
import release.movie.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

public class MovieInfoToStringTest {

    @Test
    void testShowMovie() throws ExInvalidSeatingPlan, ExNoMovieToShow {
        Movie movie = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese");
        House house = new House(1, 10, 10);
        MovieSession movieSession = new MovieSession(movie, "10:00", "12:30", house);
        Map<Integer, Movie> scheduledMovies = new LinkedHashMap<>();
        scheduledMovies.put(1, movie);

        String expected = "    Movie                           Duration     Genre           Price      Popularity     Class     Language      Subtitles     \n" +
                "----------------------------------------------------------------------------------------------------------------------------------\n" +
                " 1) Deadpool & Wolverine            170 mins     Comedy          $100.0     8.3 / 10.0     III       English       Chinese       \n";

        assertEquals(expected, MovieInfoToString.showMovie(scheduledMovies));
    }


    @Test
    void testShowMovieAdminNotScheduled() throws ExInvalidSeatingPlan, ExNoMovieToShow {
        Movie movie = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese");
        House house = new House(1, 10, 10);
        MovieSession movieSession = new MovieSession(movie, "10:00", "12:30", house);
        Map<Integer, Movie> scheduledMovies = new LinkedHashMap<>();
        scheduledMovies.put(1, movie);

        String expected = "    Movie                           Duration     Genre           Price      Popularity     Class     Language      Subtitles     Scheduled?\n" +
                "--------------------------------------------------------------------------------------------------------------------------------------------\n" +
                " 1) Deadpool & Wolverine            170 mins     Comedy          $100.0     8.3 / 10.0     III       English       Chinese          No     \n";

        assertEquals(expected, MovieInfoToString.showMovieAdmin(scheduledMovies));
    }

    @Test
    void testShowMovieAdminHasScheduled() throws ExInvalidSeatingPlan, ExNoMovieToShow {
        Movie movie = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese");
        House house = new House(1, 10, 10);
        MovieSession movieSession = new MovieSession(movie, "10:00", "12:30", house);
        movie.addMovieSession(movieSession);
        Map<Integer, Movie> scheduledMovies = new LinkedHashMap<>();
        scheduledMovies.put(1, movie);

        String expected = "    Movie                           Duration     Genre           Price      Popularity     Class     Language      Subtitles     Scheduled?\n" +
                "--------------------------------------------------------------------------------------------------------------------------------------------\n" +
                " 1) Deadpool & Wolverine            170 mins     Comedy          $100.0     8.3 / 10.0     III       English       Chinese          Yes    \n";

        assertEquals(expected, MovieInfoToString.showMovieAdmin(scheduledMovies));
    }

    @Test
    void testShowSeatingPlan() throws ExInvalidSeatingPlan {
        SeatingPlan seatingPlan = new SeatingPlan(6,10);

        String expected = "(\u001B[32m\"O\" = available; \u001B[31m\"X\" = unavailable\u001B[0m)\n" +
                "\n" +
                "       |************** SCREEN *************|\n" +
                "\n" +
                "   1\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "   2\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "   3\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "   4\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "   5\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "   6\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\u001B[32m   O\u001B[0m\n" +
                "       A   B   C   D   E   F   G   H   I   J";

        assertEquals(expected, MovieInfoToString.showSeatingPlan(seatingPlan));
    }

    @Test
    void testShowAllMovieSessions() throws ExInvalidSeatingPlan, ExNoMovieToShow {
        Movie movie = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese");
        House house = new House(1, 10, 10);
        MovieSession movieSession = new MovieSession(movie, "10:00", "12:30", house);
        Map<Integer, MovieSession> movieSessions = new HashMap<>();
        movieSessions.put(1, movieSession);

        String expected = "    Movie                           House    Seats      Start    End      Class    Language     Subtitles    Price  \n" +
                "---------------------------------------------------------------------------------------------------------------------\n" +
                " 1) Deadpool & Wolverine              1      100/100    10:00    12:30    III      English      Chinese      $100.0 \n";

        assertEquals(expected, MovieInfoToString.showAllMovieSessions(movieSessions));
    }

}