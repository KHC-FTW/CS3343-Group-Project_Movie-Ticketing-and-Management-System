package release.command;

import release.database.Database;
import release.exception.ExNoMovieToShow;
import release.movie.Movie;
import release.movie.MovieInfoToString;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CmdListMoviesAdmin implements Command {
    private final Database db = Database.getInstance();

    @Override
    public void execute(Scanner scanner) {
        List<Movie> movies = db.getMovies();
        int count = 0;
        Map<Integer, Movie> allMovies = new LinkedHashMap<>(movies.size());
        for (Movie movie: movies){
            allMovies.put(++count, movie);
        }
        try {
            System.out.println(MovieInfoToString.showMovieAdmin(allMovies));
        } catch (ExNoMovieToShow e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}