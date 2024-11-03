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
	 * Show movie sessions.
	 *
	 * @param movieSessions the movie sessions
	 * @return the string
	 * @throws CustomException the custom exception
	 */
	public static String showMovieSessions(Map<Integer, MovieSession> movieSessions) throws CustomException {
		if (movieSessions.isEmpty()) {
			throw new CustomException("No movie sessions available.");
		}
		StringBuilder result = new StringBuilder("The following sessions of \"")
									.append(movieSessions.get(1).getMovie().getName())
									.append("\" are available: \n");
		
		for (Integer key : movieSessions.keySet()) {
			result.append(String.format("%3d)%-1s", key, " "))
					.append(movieSessions.get(key).displayBasicInfo())
					.append("\n");
		}
		return result.toString();
	}
	
	/**
	 * Show seating plan.
	 *
	 * @param seatingPlan the seating plan
	 * @return the string
	 */
	public static String showSeatingPlan(SeatingPlan seatingPlan) {
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_RESET = "\u001B[0m";
		final String bookedSymbol = seatingPlan.getBookedSymbol();
		StringBuilder result = new StringBuilder("Seating Plan: (")
								.append("Seating Plan: (").append(ANSI_GREEN).append("\"O\" = available; ")
								.append(ANSI_RED).append("\"X\" = unavailable").append(ANSI_RESET).append(")\n\n")
								.append(showScreen(seatingPlan)).append(" ".repeat(4));
		char row = 'A';
		for (String seat : seatingPlan.getSeatingPlan().get(0)) {
			result.append(String.format("%4s", String.valueOf(row++)));
		}
        result.append("\n");
        for (int i = 0; i < seatingPlan.getRows(); i++) {
			for (int j = 0; j < seatingPlan.getColumns(); j++) {
				if (j == 0) result.append(String.format("%4d", i + 1));
				String currSeat = seatingPlan.getSeatingPlan().get(i).get(j);
				if (currSeat.equals(bookedSymbol)) {
					result.append(ANSI_RED).append(String.format("%4s", currSeat)).append(ANSI_RESET);
				}else{
					result.append(ANSI_GREEN).append(String.format("%4s", currSeat)).append(ANSI_RESET);
				}
			}
			result.append("\n");
		}
        return result.toString();
	}
	
	public static String showMovieSessionV1(Movie movie) {
		List<MovieSession> movieSessions = movie.getMovieSessionList();
		StringBuilder result = new StringBuilder("The following sessions of \"")
                .append(movie.getName()).append("\" are available: \n");
		for (MovieSession movieSession : movieSessions) {
			result.append(movieSession.displayBasicInfo()).append("\n");
		}
		return result.toString();
	}
	
	public static String showMovieSessionV2(Movie movie) {
		List<MovieSession> movieSessions = movie.getMovieSessionList();
		StringBuilder result = new StringBuilder();
		for (MovieSession movieSession : movieSessions) {
			result.append(movieSession).append("\n");
		}
		return result.toString();
	}
	
	/**
	 * Show screen.
	 *
	 * @param seatingPlan the seating plan
	 * @return the string
	 */
	private static String showScreen(SeatingPlan seatingPlan) {
		int screenPadding = (4 * seatingPlan.getSeatingPlan().get(0).size() - " SCREEN ".length() - 3 ) / 2;
		return new StringBuilder(" ".repeat(7))
					.append("|").append("*".repeat(screenPadding))
					.append(" SCREEN ").append("*".repeat(screenPadding - 1))
					.append("|\n\n").toString();
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
