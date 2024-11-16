package release.command;

import release.exception.ExNoMovieToShow;
import release.movie.MovieInfoToString;
import release.movie.MovieService;

import java.util.Scanner;

public class CmdListMovies implements Command {
    private final MovieService ms = MovieService.getInstance();
    @Override
    public void execute(Scanner scanner) {
        try{
            System.out.println(MovieInfoToString.showMovie(ms.getAllScheduledMovies()));
        }catch (ExNoMovieToShow e){
            System.out.println(e.getMessage() + "\n");
        }
    }
}
