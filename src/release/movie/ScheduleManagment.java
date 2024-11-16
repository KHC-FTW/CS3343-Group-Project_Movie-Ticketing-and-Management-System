package release.movie;

import java.util.ArrayList;
import java.util.List;

import release.database.Database;
import release.exception.ExNoMovieToSchedule;

public class ScheduleManagment {
	private static final Database db = Database.getInstance();
	private List<Movie> movies;
	private Schedule schedule;
	private List<MovieSession> movieSessions;
	private boolean hasScheduled;

	private ScheduleManagment(){
		this.movies = db.getMovies();
		this.schedule = new ProfitMaximizationSchedule(this.movies);
		this.movieSessions = new ArrayList<>();
		hasScheduled = false;
	}

	private static final ScheduleManagment instance = new ScheduleManagment();

	public static ScheduleManagment getInstance(){return instance;}
//	public ScheduleManagment(List<Movie> movies) {
//		this.movies = movies;
//		this.schedule = new ProfitMaximizationSchedule(this.movies);
//		this.movieSessions = new ArrayList<>();
//	}
	
	public void changeSchedulingStrategies(String command) {
		if(command.equals("1")) {
			schedule =  new ProfitMaximizationSchedule(movies);
		}else {
			schedule =  new BalancedSchedule(movies);
		}
	}
	
	public String showSchedulingStrategies() {
		return schedule.getSchedulingName();
	}
	
	public void setScheduleSlot() throws ExNoMovieToSchedule {
		schedule.scheduling();
		this.movieSessions = schedule.getScheduleSessions();
	}
	
	public List<MovieSession> getScheduleSlot() {
		return movieSessions;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}

	public boolean getScheduleStatus(){
		return hasScheduled;
	}

	public boolean setHasScheduledStatus(){
		return hasScheduled = true;
	}
}


