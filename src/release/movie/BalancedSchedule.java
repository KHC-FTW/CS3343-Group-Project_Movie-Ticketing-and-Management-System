package release.movie;

import release.exception.ExNoMovieToSchedule;
import release.helper.Time;

import java.util.Collections;
import java.util.List;

public class BalancedSchedule extends Schedule {
	
	public BalancedSchedule(List<Movie> movies) {
		super("Balanced Scheduling", movies);
	}
	
	public void scheduling() throws ExNoMovieToSchedule {
		List<Movie> movies = db.getMovies();
		List<House> houses = db.getHouses();

        if (movies.isEmpty()) throw new ExNoMovieToSchedule();
		
		int openHours = Time.getHourFromTimeString(db.getOpenHours());
		
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
