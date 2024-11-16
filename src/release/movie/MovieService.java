package release.movie;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import release.database.Database;
import release.exception.CustomException;
import release.exception.ExInvalidOption;
import release.exception.ExInvalidSearch;
import release.exception.ExNoMovieToShow;
import release.user.User;

/**
 * The Class MovieService.
 */
public class MovieService {
	
	/** The Constant cinemaDatabase. */
	private static final Database db = Database.getInstance();
	private MovieService() {}
	private static final MovieService movieService = new MovieService();
	
	public static MovieService getInstance() {return movieService;}
	
	/**
	 * Gets the scheduled movies as Movie objects.
	 *
	 * @return the scheduled movies
	 * @throws ExNoMovieToShow the custom exception
	 */
	public Map<Integer, Movie> getAllScheduledMovies() throws ExNoMovieToShow {
		Map<Integer, Movie> scheduledMovies = new LinkedHashMap<>();
		int count = 0;
		for (Movie movie : db.getMovies()) {
			if (isScheduled(movie)) {
				count++;
				scheduledMovies.put(count, movie);
			}
		}
		if (scheduledMovies.isEmpty()) {
			throw new ExNoMovieToShow();
		}
		return scheduledMovies;
	}

	/**
	 * Search scheduled movies as Movie objects.
	 *
	 * @param name the movie name to search
	 * @return the list
	 * @throws ExInvalidSearch the custom exception
	 */
	public Map<Integer, Movie> searchScheduledMovies(String name) throws ExInvalidSearch{
		String searchName = name.strip().toLowerCase();
		Map<Integer, Movie> searchResults = new LinkedHashMap<Integer, Movie>();
		int count = 0;
		for (Movie movie : db.getMovies()) {
			if (isScheduled(movie) && movie.getName().toLowerCase().contains(searchName)) {
				count++;
				searchResults.put(count, movie);
			}
		}
		if (searchResults.isEmpty()) {
			throw new ExInvalidSearch("There are no movies matched with the search keyword.");
		}
		return searchResults;
	}
	
	/**
	 * Search movies for admin.
	 *
	 * @param name the name
	 * @return the list
	 * @throws ExInvalidSearch
	 */
	public Map<Integer, Movie> searchMoviesAdmin(String name) throws ExInvalidSearch {
		String searchName = name.strip().toLowerCase();
		Map<Integer, Movie> searchResults = new LinkedHashMap<Integer, Movie>();
		int count = 0;
		for (Movie movie : db.getMovies()) {
			if (movie.getName().toLowerCase().contains(searchName)) {
				count++;
				searchResults.put(count, movie);
			}
		}
		if (searchResults.isEmpty()) {
			throw new ExInvalidSearch("There are no movies matched with the search keyword.");
		}
		return searchResults;
	}

	/**
	 * Gets the selected movie session.
	 *
	 * @param movieSessions the movie sessions
	 * @param selected the selected
	 * @return the selected movie session
	 * @throws CustomException the custom exception
	 */
	public MovieSession getSelectedMovieSession(Map<Integer, MovieSession> movieSessions, int selected)
			throws ExInvalidOption {
		if (!movieSessions.containsKey(selected)) {
			throw new ExInvalidOption();
		}
		return movieSessions.get(selected);
	}
	
	/**
	 * Checks if is scheduled.
	 *
	 * @param movie the movie
	 * @return true, if is scheduled
	 */
	// Refactored method: extract a method
	public boolean isScheduled(Movie movie) {
		return !movie.getMovieSessionList().isEmpty();
	}

	public Map<Integer, MovieSession> getAllMovieSessions() throws ExNoMovieToShow {
		List<Movie> movies = db.getMovies();
		Map<Integer, MovieSession> results = new LinkedHashMap<>();
//		if(movies.isEmpty()) throw new CustomException("There are no movie sessions to show.");
		int count = 0;
		for (Movie movie: movies){
			List<MovieSession> moviesSessions = movie.getMovieSessionList();
			for (MovieSession movieSession: moviesSessions){
				results.put(++count, movieSession);
			}
		}
		if(results.isEmpty()) throw new ExNoMovieToShow("There are no movie sessions to show.");
		return results;
	}

	/**
	 * Gets the all movies for admin.
	 *
	 * @return the all movies for admin
	 */
//	public Map<Integer, Movie> getAllMoviesAdmin() {
//		Map<Integer, Movie> movies = new LinkedHashMap<Integer, Movie>();
//		for (int i = 0; i < db.getMovies().size(); i++) {
//			movies.put(i + 1, db.getMovies().get(i));
//		}
//		return movies;
//	}

	/**
	 //	 * Gets the selected movie.
	 //	 *
	 //	 * @param movies the movies
	 //	 * @param selected the selected
	 //	 * @return the selected movie
	 //	 * @throws CustomException the custom exception
	 //	 */
//	public Movie getSelectedMovie(Map<Integer, Movie> movies, int selected, User user) throws CustomException {
//		if (!movies.containsKey(selected)) {
//			throw new CustomException("[Exception] Invalid movie selection.");
//		}
//		Movie movie = movies.get(selected);
//		if (movie.getClassification().equals("III") && user.getAge() < 18){
//			throw new CustomException("You are not allowed to watch class III movies.");
//		}
//		return movie;
//	}

	/**
	 * Gets the movie sessions.
	 *
	 * @param movie the movie
	 * @return the movie sessions
	 * @throws CustomException the custom exception
	 */
//	public Map<Integer, MovieSession> getMovieSessions(Movie movie) throws CustomException{
//		List<MovieSession> movieSessions = movie.getMovieSessionList();
//		if(movieSessions == null || movieSessions.isEmpty()) {
//			throw new CustomException("No movie sessions available for this movie.");
//		}
//		Map<Integer, MovieSession> results = new LinkedHashMap<Integer, MovieSession>();
//		int count = 0;
//		for (MovieSession movieSession : movieSessions) {
//			count++;
//			results.put(count, movieSession);
//		}
//		return results;
//	}
	
}
