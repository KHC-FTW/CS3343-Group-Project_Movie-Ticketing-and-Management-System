package release.movie;
import java.util.ArrayList;
import java.util.List;

import release.exception.CustomException;

public class House implements Comparable<House>{
	private int houseNumber;
	private final SeatingPlan defaultSeatingPlan;
	private List<MovieSession> movieSessions;

	
	public House() throws CustomException {
		this.defaultSeatingPlan = new SeatingPlan();
		this.houseNumber = 0;
	}
	
	public House(int roomNumber, int rows, int columns) throws CustomException {
		this.defaultSeatingPlan = new SeatingPlan(rows, columns);
		this.houseNumber = roomNumber;
	}
	
	public SeatingPlan getNewSeatingPlanForNewMovieSession() {
		try {
			return new SeatingPlan(defaultSeatingPlan.getRows(), defaultSeatingPlan.getColumns());
		} catch (CustomException e) {
			return null;
		}
	}

	public int getHouseNumber() {
		return this.houseNumber;
	}
	
	public List<MovieSession> addMovieSession(MovieSession movieSession) {
		if (movieSessions == null) {
			movieSessions = new ArrayList<>();
		}
		movieSessions.add(movieSession);
		return movieSessions;
	}
	
	public List<MovieSession> getMovieSessions() {
        return movieSessions;
    }

	@Override
	public int compareTo(House other) {
		return Integer.compare(this.houseNumber, other.getHouseNumber());
	}
	
	@Override
	public String toString() {
		return "House number: " + houseNumber;
	}
	
//	@Override
//	public String toString() {
//		StringBuilder result = new StringBuilder();
//		result.append("Room number: " + this.houseNumber);
//		List<Movie> scheduledMovies = getScheduledMovies();
//		if (scheduledMovies == null) {
//			return result.toString();
//		} else {
//			result.append("\n");
//		}
//		for (Movie movie : scheduledMovies) {
//			result.append("Movie: " + movie.getName() + "\n");
//			result.append(movieSeatingPlan.get(movie).displaySeatingPlan());
//		}
//		return result.toString();
//	}
}
