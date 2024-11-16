package release.command;

import release.exception.ExNoMovieToShow;
import release.movie.MovieInfoToString;
import release.movie.MovieService;
import release.movie.MovieSession;

import java.util.Map;
import java.util.Scanner;

public class CmdListMovieSessions implements Command {
    private final MovieService ms = MovieService.getInstance();

    @Override
    public void execute(Scanner scanner) {
        try{
            Map<Integer, MovieSession> movieSessions = ms.getAllMovieSessions();
            System.out.println(MovieInfoToString.showAllMovieSessions(movieSessions));
        }catch (ExNoMovieToShow e){
            System.out.println(e.getMessage() + "\n");
        }
    }
}
