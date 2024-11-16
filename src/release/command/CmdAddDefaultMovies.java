package release.command;

import release.database.DefaultData;
import release.exception.ExNoMovieToShow;
import release.movie.Movie;
import release.movie.MovieInfoToString;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CmdAddDefaultMovies implements Command {
    private final DefaultData dd = DefaultData.getInstance();
    @Override
    public void execute(Scanner scanner) {
        if(dd.getMoviesLoadedStatus()){
            System.out.println("[Exception] Default movies have already been loaded. It can be done once only.\n");
            return;
        }
        String input = "";
        List<Movie> movies = dd.getDefaultMovies();
        int count = 0;
        Map<Integer, Movie> moviesToAdd = new LinkedHashMap<>(movies.size());
        for (Movie movie: movies){
            moviesToAdd.put(++count, movie);
        }
        try{
            System.out.println("Below is the list of default movies:");
            System.out.println(MovieInfoToString.showMovieAdmin(moviesToAdd));
            // can ignore exception here
            // since default movie list is guaranteed not to be empty
        }catch (ExNoMovieToShow ignored) {}

        do{
            System.out.print("Confirm to add? (Y/N)\n> ");
            input = scanner.nextLine().strip();
            System.out.println();
            if(input.equalsIgnoreCase("Y")){
                dd.loadDefaultMoviesToDB();
                System.out.println("[State] Successfully added all default movies to the database.\n");
                break;
            }else if(input.equalsIgnoreCase("N") || input.equals("q") || input.equals("quit")){
                System.out.println("[State] Add default movies process terminated.\n");
                break;
            }else{
                System.out.println("[Exception] Invalid command. Please try again.\n");
            }
        }while(true);
    }
}
