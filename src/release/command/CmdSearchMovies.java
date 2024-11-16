package release.command;

import release.database.Database;
import release.exception.ExInvalidSearch;
import release.exception.ExNoMovieToShow;
import release.movie.Movie;
import release.movie.MovieInfoToString;
import release.movie.MovieService;
import release.user.Admin;
import release.user.User;
import release.user.UserCenter;

import java.util.Map;
import java.util.Scanner;

public class CmdSearchMovies implements Command{
    private final MovieService ms = MovieService.getInstance();
    private final UserCenter uc = UserCenter.getInstance();
    private final Database db = Database.getInstance();
    @Override
    public void execute(Scanner scanner) {
        if(db.getMovies().isEmpty()){
            System.out.println("[Excepton] Search function is disabled as there are currently no movies available for search.\n");
            return;
        }
        User user = uc.getCurrentUser();
        System.out.print("Please enter a keyword to search:\n> ");
        String input = scanner.nextLine().strip();
        System.out.println();
        try{
            if (user instanceof Admin) {
                Map<Integer, Movie> results = ms.searchMoviesAdmin(input);
                System.out.println(MovieInfoToString.showMovieAdmin(results));
                return;
            }
            Map<Integer, Movie> results = ms.searchScheduledMovies(input);
            System.out.println(MovieInfoToString.showMovie(results));
        }catch(ExNoMovieToShow | ExInvalidSearch e){
            System.out.println(e.getMessage() + "\n");
        }
    }
}
