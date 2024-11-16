package release.movie;

import java.util.Collections;
import java.util.List;

import release.exception.ExNoMovieToSchedule;
import release.helper.Time;

public class ProfitMaximizationSchedule extends Schedule {
	
	public ProfitMaximizationSchedule(List<Movie> movies) {
		super("Profit Maximization Scheduling", movies);
	}
	
	public void scheduling() throws ExNoMovieToSchedule {
		List<Movie> movies = db.getMovies();
		List<House> houses = db.getHouses();

		if (movies.isEmpty()) throw new ExNoMovieToSchedule();
		Collections.sort(movies);
		
		int openHours = Time.getHourFromTimeString(db.getOpenHours());
		
		final int firstHalf = houses.size() / 2;
		for(int i = 0; i < houses.size(); i++) {
			if(i < firstHalf) {
				Movie topMovie = movies.get(i);
				scheduleTopMovie(topMovie, houses.get(i));
			}
			else {
				availableHouses.put(houses.get(i), openHours * 60);
			}
		}
		
	    int i = firstHalf;
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            House nextAvailableHouse = findNextAvailableHouse();
            scheduleMovie(movie, nextAvailableHouse);
            i++;
            if(i == movies.size()) {
            	i = firstHalf;
            }       	           
        }	
	}
	
	private void scheduleTopMovie(Movie movie, House house) {
		int closeHours = Time.getHourFromTimeString(db.getCloseHours());
		int openHours = Time.getHourFromTimeString(db.getOpenHours());
		int availableTime = (closeHours - openHours) * 60;  // Total minutes available in a day
	    int startTime = openHours * 60;  // Start time in minutes from midnight
	    
	    while (availableTime >= movie.getDuration()) {
	    	int endTime = startTime + movie.getDuration();
	    	
	    	String formattedStartTime = Time.convertMinsToTimeString(startTime);
	        String formattedEndTime = Time.convertMinsToTimeString(endTime);
	         
	        MovieSession movieSession = new MovieSession(movie, formattedStartTime, formattedEndTime, house);
	        movie.addMovieSession(movieSession);
	        house.addMovieSession(movieSession);
	        addScheduleSession(movieSession);
	        
	        startTime = endTime + getBufferTime(); 
	        availableTime -= (movie.getDuration() + getBufferTime());
	    }
	}
}
