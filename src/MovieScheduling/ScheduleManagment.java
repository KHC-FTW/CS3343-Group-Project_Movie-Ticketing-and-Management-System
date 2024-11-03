package MovieScheduling;

import java.util.ArrayList;
import java.util.List;

import selectMovieModule.*;

public class ScheduleManagment {
	private List<Movie> movies;
	private Schedule schedule;
	private List<MovieSession> movieSessions;
	
	public ScheduleManagment(List<Movie> movies) {
		this.movies = movies;
		this.schedule = new ProfitMaximizationSchedule(this.movies);
		this.movieSessions = new ArrayList<>(); 
	}
	
	public void changeSchedulingStrategies(String command) {
		if(command == "1") {
			schedule =  new ProfitMaximizationSchedule(movies);
		}else {
			schedule =  new BalancedSchedule(movies);
		}
	}
	
	public String showSchedulingStrategies() {
		return schedule.getSchedulingName();
	}
	
	public void setScheduleSlot() {
		schedule.scheduling();
		this.movieSessions = schedule.getScheduleSessions();
	}
	
	public List<MovieSession> getScheduleSlot() {
		return movieSessions;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
}


