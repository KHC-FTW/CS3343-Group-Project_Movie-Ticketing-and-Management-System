package release.movie;

import release.exception.CustomException;
import release.exception.ExNoMovieToShow;

import java.util.List;
import java.util.Map;

public class MovieInfoToString {
	private final static MovieService ms = MovieService.getInstance();
	/**
	 * Show scheduled movies as string.
	 *
	 * @param scheduledMovies the scheduled movies
	 * @return the string
	 * @throws ExNoMovieToShow the custom exception
	 */
	public static String showMovie(Map<Integer, Movie> scheduledMovies) throws ExNoMovieToShow {
		if (scheduledMovies.isEmpty()) {
			throw new ExNoMovieToShow();
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
	 * @throws ExNoMovieToShow the custom exception
	 */
	public static String showMovieAdmin(Map<Integer, Movie> movies) throws ExNoMovieToShow {
		if (movies.isEmpty()) {
			throw new ExNoMovieToShow("There are no movies to show. Consider adding some movies to the database first.");
		}
		StringBuilder result = new StringBuilder(showMovieInfoHeaderAdmin());
		for (Integer key : movies.keySet()) {
			result.append(showMovieInfoAdmin(key, movies.get(key))).append("\n");
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
		StringBuilder result = new StringBuilder("(")
								.append(ANSI_GREEN).append("\"O\" = available; ")
								.append(ANSI_RED).append("\"X\" = unavailable").append(ANSI_RESET).append(")\n\n")
								.append(showScreen(seatingPlan));

        for (int row = 0; row < seatingPlan.getRows(); row++) {
			for (int col = 0; col < seatingPlan.getColumns(); col++) {
				if (col == 0) result.append(String.format("%4d", row + 1));
				String currSeat = seatingPlan.getSeatingPlan().get(row).get(col);
				if (currSeat.equals(bookedSymbol)) {
					result.append(ANSI_RED).append(String.format("%4s", currSeat)).append(ANSI_RESET);
				}else{
					result.append(ANSI_GREEN).append(String.format("%4s", currSeat)).append(ANSI_RESET);
				}
			}
			result.append("\n");
		}
		char colChar = 'A';
		result.append(" ".repeat(4));
		for (String seat : seatingPlan.getSeatingPlan().get(0)) {
			result.append(String.format("%4s", String.valueOf(colChar++)));
		}
        return result.toString();
	}

	public static String showAllMovieSessions(Map<Integer, MovieSession> movieSessions) throws ExNoMovieToShow {
		if (movieSessions.isEmpty()) throw new ExNoMovieToShow("There are no movie sessions to show.");

		StringBuilder results = new StringBuilder(String.format("%-4s%-32s", " ", "Movie"))
				.append(String.format("%-9s", "House")).append(String.format("%-11s", "Seats"))
				.append(String.format("%-9s", "Start")).append(String.format("%-9s", "End"))
				.append(String.format("%-9s", "Class")).append(String.format("%-13s", "Language"))
				.append(String.format("%-13s", "Subtitles")).append(String.format("%-7s", "Price"))
				.append("\n").append("-".repeat(117)).append("\n");

		// Class, Language, Subtitles, Price

		for (Integer key: movieSessions.keySet()){
			MovieSession current = movieSessions.get(key);
			results.append(String.format("%2d)%-1s", key, " "))
					.append(String.format("%-30s%-2s", current.getMovie().getName(), " "))
					.append(String.format("%-2s%-7d", " ", current.getHouse().getHouseNumber()))
					.append(String.format("%-3d%1s%3d%4s", current.getSeats().getRemainingSeats(), "/", current.getSeats().getTotalSeats(), " "))
					.append(String.format("%-9s", current.getStartTime()))
					.append(String.format("%-9s", current.getEndTime()))
					.append(String.format("%-9s", current.getMovie().getClassification()))
					.append(String.format("%-13s", current.getMovie().getLanguage()))
					.append(String.format("%-13s", current.getMovie().getSubtitles()))
					.append(String.format("$%-6.1f", current.getMovie().getPrice()))
					.append("\n");
		}
		return results.toString();
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
		return new StringBuilder(String.format("%4s", " "))
				.append(String.format("%-32s", "Movie"))
				.append(String.format("%-13s", "Duration"))
				.append(String.format("%-16s", "Genre"))
				.append(String.format("%-11s", "Price"))
				.append(String.format("%-15s", "Popularity"))
				.append(String.format("%-10s", "Class"))
				.append(String.format("%-14s", "Language"))
				.append(String.format("%-14s", "Subtitles"))
				.toString();
	}
	
	/**
	 * Show movie info header.
	 *
	 * @return the string
	 */
	private static String showMovieInfoHeader() {
		return (new StringBuilder(defaultMovieInfoHeader()))
				.append("\n").append("-".repeat(130)).append("\n").toString();
	}
	
	/**
	 * Show movie info.
	 *
	 * @param num the num
	 * @param movie the movie
	 * @return the string
	 */
	private static String showMovieInfo(int num, Movie movie) {
		return new StringBuilder(String.format("%2d)%1s", num, " "))
				.append(String.format("%-30s%2s", movie.getName(), " "))
		        .append(String.format("%-3d %-9s", movie.getDuration(), "mins"))
		        .append(String.format("%-16s", movie.getGenre()))
		        .append(String.format("$%-10.1f", movie.getPrice()))
				.append(String.format("%-4.1f%-11s", movie.getPopularityScore(), "/ 10.0"))
		        .append(String.format("%-10s", movie.getClassification()))
		        .append(String.format("%-14s", movie.getLanguage()))
		        .append(String.format("%-14s", movie.getSubtitles()))
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
				.append("\n").append("-".repeat(140)).append("\n").toString();
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
		if (ms.isScheduled(movie)) result.append(String.format("%-10s", "   Yes"));
		else result.append(String.format("%-10s", "   No"));
		return result.toString();
	}

	/**
	 * Show movie sessions.
	 *
	 * @param movieSessions the movie sessions
	 * @return the string
	 * @throws CustomException the custom exception
	 */
//	public static String showMovieSessions(Map<Integer, MovieSession> movieSessions) throws CustomException {
//		if (movieSessions.isEmpty()) {
//			throw new CustomException("No movie sessions available.");
//		}
//		StringBuilder result = new StringBuilder("The following sessions of \"")
//									.append(movieSessions.get(1).getMovie().getName())
//									.append("\" are available: \n");
//
//		for (Integer key : movieSessions.keySet()) {
//			result.append(String.format("%3d)%-1s", key, " "))
//					.append(movieSessions.get(key).displayBasicInfo())
//					.append("\n");
//		}
//		return result.toString();
//	}

	//	public static String showMovieSessionV1(Movie movie) {
//		List<MovieSession> movieSessions = movie.getMovieSessionList();
//		StringBuilder result = new StringBuilder("The following sessions of \"")
//                .append(movie.getName()).append("\" are available: \n");
//		for (MovieSession movieSession : movieSessions) {
//			result.append(movieSession.displayBasicInfo()).append("\n");
//		}
//		return result.toString();
//	}
//
//	public static String showMovieSessionV2(Movie movie) {
//		List<MovieSession> movieSessions = movie.getMovieSessionList();
//		StringBuilder result = new StringBuilder();
//		for (MovieSession movieSession : movieSessions) {
//			result.append(movieSession).append("\n");
//		}
//		return result.toString();
//	}
	
}
