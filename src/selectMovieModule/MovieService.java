package selectMovieModule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import selectMovieModule.Movie;
/**
 * The Class MovieService.
 */
public class MovieService {
	
	/** The Constant cinemaDatabase. */
	private static final CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance(); 
	
	/**
	 * Gets the scheduled movies as Movie objects.
	 *
	 * @return the scheduled movies
	 * @throws CustomException the custom exception
	 */
	public static List<Movie> getAllScheduledMovies() throws CustomException {
		List<Movie> scheduledMovies = new ArrayList<Movie>();
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (isScheduled(movie)) scheduledMovies.add(movie);
		}
		if (scheduledMovies.isEmpty()) {
			throw new CustomException("Sorry! There are currently no scheduled movies.");
		}
		return scheduledMovies;
	}
	
	/**
	 * Gets the all movies for admin.
	 *
	 * @return the all movies for admin
	 */
	public static List<Movie> getAllMoviesAdmin() {
		return cinemaDatabase.getMovies();
	}
	
	/**
	 * Search scheduled movies as Movie objects.
	 *
	 * @param name the movie name to search
	 * @return the list
	 * @throws CustomException the custom exception
	 */
	public static List<Movie> searchScheduledMovies(String name) throws CustomException{
		String searchName = name.strip().toLowerCase();
		List<Movie> searchResults = new ArrayList<Movie>();
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (isScheduled(movie) && movie.getName().toLowerCase().contains(searchName)) {
				searchResults.add(movie);
			}
		}
		if (searchResults.isEmpty()) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return searchResults;
	}
	
	/**
	 * Search movies for admin.
	 *
	 * @param name the name
	 * @return the list
	 * @throws CustomException 
	 */
	public static List<Movie> searchMoviesAdmin(String name) throws CustomException {
		String searchName = name.strip().toLowerCase();
		List<Movie> searchResults = new ArrayList<Movie>();
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getName().toLowerCase().contains(searchName)) {
				searchResults.add(movie);
			}
		}
		if (searchResults.isEmpty()) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return searchResults;
	}
	
	/**
	 * Show scheduled movies as string.
	 *
	 * @param scheduledMovies the scheduled movies
	 * @return the string
	 * @throws CustomException the custom exception
	 */
	public static String showMovieAsString(List<Movie> scheduledMovies) throws CustomException{
		StringBuilder result = new StringBuilder(showMovieInfoHeader());
		int count = 0;
		for (Movie movie : scheduledMovies) {
				count++;
				result.append(showMovieInfo(count, movie)).append("\n");
		}
		if (count == 0) {
			throw new CustomException("No movies can be shown.");
		}
		return result.toString();
	}
	
	/**
	 * Show movie as string admin.
	 *
	 * @param movies the movies
	 * @return the string
	 * @throws CustomException the custom exception
	 */
	public static String showMovieAsStringAdmin(List<Movie> movies) throws CustomException {
		StringBuilder result = new StringBuilder(showMovieInfoHeaderAdmin());
		int count = 0;
		for (Movie movie : movies) {
			count++;
			result.append(showMovieInfoAdmin(count, movie)).append("\n");
		}
		if (count == 0) {
			throw new CustomException("No movies can be shown.");
		}
		return result.toString();
	}
	
	/**
	 * Default movie info header.
	 *
	 * @return the string
	 */
	private static String defaultMovieInfoHeader() {
		return (new StringBuilder(String.format("%-5s", " ")))
				.append(String.format("%-30s%-2s", "Movie", " "))
				.append(String.format("%-4s%-6s", "Duration", " "))
				.append(String.format("%-12s", "Genre"))
				.append(String.format("%-9s", "Price"))
				.append(String.format("%-8s", "Class"))
				.append(String.format("%-12s", "Language"))
				.append(String.format("%-12s", "Subtitles"))
				.toString();
	}
	
	/**
	 * Show movie info header.
	 *
	 * @return the string
	 */
	private static String showMovieInfoHeader() {
		return (new StringBuilder(defaultMovieInfoHeader()))
				.append("\n")
				.append("------------------------------------------------------------------------------------------------------\n")
				.toString();
	}
	
	/**
	 * Show movie info.
	 *
	 * @param num the num
	 * @param movie the movie
	 * @return the string
	 */
	private static String showMovieInfo(int num, Movie movie) {
		return (new StringBuilder(String.format("%3d)%-1s", num, " ")))
				.append(String.format("%-30s%-2s", movie.getName(), " "))
		        .append(String.format("%-4dmins%-6s", movie.getDuration(), " "))
		        .append(String.format("%-12s", movie.getGenre()))
		        .append(String.format("$%-8.0f", movie.getPrice()))
		        .append(String.format("%-8s", movie.getClassification()))
		        .append(String.format("%-12s", movie.getLanguage()))
		        .append(String.format("%-12s", movie.getSubtitles()))
		        .append("\n")
		        .toString();
	}

	/**
	 * Show movie info header for admin.
	 *
	 * @return the string
	 */
	private static String showMovieInfoHeaderAdmin() {	
		return new StringBuilder(defaultMovieInfoHeader())
				.append(String.format("%-10s", "Scheduled?"))
				.append("\n")
				.append("--------------------------------------------------------------------------------------------------------------------\n")
				.toString();
	}

	/**
	 * Show movie info for admin.
	 *
	 * @param num the num
	 * @param movie the movie
	 * @return the string
	 */
	private static String showMovieInfoAdmin(int num, Movie movie){
		StringBuilder result = new StringBuilder(showMovieInfo(num, movie));
		List<MovieSession> movieSessionList = movie.getMovieSessionList();
		if (isScheduled(movie)) result.append(String.format("%-10s", "Yes"));
		else result.append(String.format("%-10s", "No"));
		return result.append("\n").toString();
	}
	
	
	/**
	 * Checks if is scheduled.
	 *
	 * @param movie the movie
	 * @return true, if is scheduled
	 */
	// Refactored method: extract a method
	private static boolean isScheduled(Movie movie) {
		return movie.getMovieSessionList() != null && !movie.getMovieSessionList().isEmpty();
	}
}
