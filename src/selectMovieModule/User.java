package selectMovieModule;

import java.util.List;
import java.util.Map;

public class User{
	
	protected final MovieService movieService = MovieService.getInstance();
	
	public Map<Integer, Movie> listMovies() throws CustomException {
		return movieService.getAllScheduledMovies();
	}
	
	public Map<Integer, Movie> searchMovies(String name) throws CustomException {
		return movieService.searchScheduledMovies(name);
	}
	
}
