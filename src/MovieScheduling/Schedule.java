package MovieScheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import selectMovieModule.*;

public abstract class Schedule {
	private String schedulingName;
	private List<Movie> movies;
	private List<MovieSession> movieSessions;
	protected CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
	protected Map<House, Integer> availableHouses;
	protected final int BUFFER_TIME = 15;
	
    public Schedule(String schedulingName, List<Movie> movies) {
    	this.schedulingName = schedulingName;
        this.movies = movies;
        this.movieSessions = new ArrayList<>(); 
        this.availableHouses = new HashMap<>();
    }
    
	public abstract void scheduling();
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	public void addScheduleSession(MovieSession movieSession) {
		movieSessions.add(movieSession);
	}
	
	public List<MovieSession> getScheduleSessions() {
		return movieSessions;
	}

	public String getSchedulingName() {
        return schedulingName;
    }
	
	public int getBufferTime() {
		return BUFFER_TIME;
	}
	
	public void scheduleMovie(Movie movie, House house) {
        int startTime = availableHouses.get(house);  
        int endTime = startTime + movie.getDuration();

        if (endTime <= Time.getHourFromTimeString(cinemaDatabase.getCloseHours()) * 60) {
            String formattedStartTime = Time.convertMinsToTimeString(startTime);
            String formattedEndTime = Time.convertMinsToTimeString(endTime);

            MovieSession movieSession = new MovieSession(movie, formattedStartTime, formattedEndTime, house);
            movie.addMovieSession(movieSession);
            house.addMovieSession(movieSession);
            addScheduleSession(movieSession);
           
            availableHouses.put(house, endTime + getBufferTime());
        }
    }
	
	public House findNextAvailableHouse() {
        return availableHouses.entrySet().stream()
                .min((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))  // Compare based on available time
                .get().getKey();
    }
	
	public boolean checkAvailableSlots(List<Movie> movies) {
	    // Check if any movie can still fit in the available time of any theater
	    for (House house : availableHouses.keySet()) {
	        int availableTime = availableHouses.get(house);
	        
	        // Check if any movie can fit in this theater
	        for (int i = 1; i < movies.size(); i++) {
	        	Movie movie = movies.get(i);
	        	
	            if (movie.getDuration() + availableTime <= Time.getHourFromTimeString(cinemaDatabase.getCloseHours()) * 60) {
	                return true;  // There's still room for at least one movie
	            }
	        }
	    }
	    return false;  // No more room for any movie in any theater
	}	
}
