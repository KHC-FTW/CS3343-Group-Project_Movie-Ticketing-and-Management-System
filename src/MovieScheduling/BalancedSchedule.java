package MovieScheduling;

import java.util.Collections;
import java.util.List;

import selectMovieModule.House;
import selectMovieModule.Movie;

public class BalancedSchedule extends Schedule {
	
	public BalancedSchedule(List<Movie> movies) {
		super("Balanced Scheduling", movies);
	}
	
	public void scheduling() {
		List<Movie> movies = cinemaDatabase.getMovies();
		List<House> houses = cinemaDatabase.getHouses();
		
		int openHours = Time.getHourFromTimeString(cinemaDatabase.getOpenHours());
		
		for(int i = 0; i < houses.size(); i++) {
			availableHouses.put(houses.get(i), openHours * 60);
		}
		
	    int i = 0;
	    
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            House nextAvailableTheater = findNextAvailableHouse();
            scheduleMovie(movie, nextAvailableTheater);
            i++;
            if(i == movies.size()) {
            	i = 0;
            	break;
            }       	           
        }
        
        Collections.sort(movies);
        
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            
            House nextAvailableTheater = findNextAvailableHouse();
            scheduleMovie(movie, nextAvailableTheater);
            i++;
            if(i == movies.size()) {
            	i = 0;
            }       	           
        }   
	}
	
}
