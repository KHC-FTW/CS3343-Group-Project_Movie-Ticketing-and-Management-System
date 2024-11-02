package selectMovieModule;

import java.util.List;

public class MovieSession {
	Movie movie;
	Time showTime;
	House house;
	SeatingPlan seats;
	
	public MovieSession(Movie movie) throws CustomException {
		this.movie = movie;
		this.showTime = new Time(0, 0);
		this.house = new House();
		this.seats = house.getNewSeatingPlanForNewMovieSession();
	}
		
	public MovieSession(Movie movie, Time showTime, House house) {
		this.movie = movie;
		this.showTime = showTime;
		this.house = house;
		this.seats = house.getNewSeatingPlanForNewMovieSession();
	}
	
	public Movie getMovie() {return movie;}
	
	public Time getShowTime() {return showTime;}
	
	public House getHouse() {return house;}
	
	public SeatingPlan getSeats() {return seats;}
	
	public String displayBasicInfo() {
		return "House: " + house.getHouseNumber() + ", Show Time: " + showTime;
	}

	
}
