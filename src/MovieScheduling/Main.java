package MovieScheduling;

import java.util.Collections;
import java.util.List;

import selectMovieModule.*;


public class Main {
	 public static void main(String[] args) {
	        
	        DefaultData.loadDefaultDataToDB();
	        
	        CinemaDatabase db = CinemaDatabase.getInstance();

	        ScheduleManagment scheduleManagment = new ScheduleManagment(db.getMovies());
	        
	        scheduleManagment.changeSchedulingStrategies("1");
	        
	        scheduleManagment.setScheduleSlot();
	        List<MovieSession> movieSession = scheduleManagment.getScheduleSlot();
//	        Collections.sort(db.getHouses());
	        Collections.sort(db.getMovies()); 
	        
			for (Movie movie : db.getMovies()) {
				System.out.println(ToStringUtil.showMovieSessionV1(movie));
			}
			
			for (Movie movie : db.getMovies()) {
				System.out.print(ToStringUtil.showMovieSessionV2(movie));
			}
			
			System.out.println();
			for (House house : db.getHouses()) {
				for (MovieSession slot : house.getMovieSessions()) {
					System.out.println(slot);
				}
			}
	        
//	        for(MovieSession slot : movieSession) {
//	        	System.out.println(slot);
//	        }
	    }
}
