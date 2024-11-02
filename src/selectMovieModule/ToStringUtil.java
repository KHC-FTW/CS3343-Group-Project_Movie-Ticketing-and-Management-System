package selectMovieModule;

import java.util.List;
import java.util.Map;

public class ToStringUtil {
	/**
	 * Show scheduled movies as string.
	 *
	 * @param scheduledMovies the scheduled movies
	 * @return the string
	 * @throws CustomException the custom exception
	 */
	public static String showMovie(Map<Integer, Movie> scheduledMovies) throws CustomException{
		if (scheduledMovies.isEmpty()) {
			throw new CustomException("No movies can be shown.");
		}
		StringBuilder result = new StringBuilder(showMovieInfoHeader());
		for (Integer key : scheduledMovies.keySet()) {
			result.append(showMovieInfo(key, scheduledMovies.get(key))).append("\n");
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
	public static String showMovieAdmin(Map<Integer, Movie> movies) throws CustomException {
		if (movies.isEmpty()) {
			throw new CustomException("No movies can be shown.");
		}
		StringBuilder result = new StringBuilder(showMovieInfoHeaderAdmin());
		for (Integer key : movies.keySet()) {
			result.append(showMovieInfoAdmin(key, movies.get(key))).append("\n");
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
		if (MovieService.isScheduled(movie)) result.append(String.format("%-10s", "Yes"));
		else result.append(String.format("%-10s", "No"));
		return result.append("\n").toString();
	}
	
}
