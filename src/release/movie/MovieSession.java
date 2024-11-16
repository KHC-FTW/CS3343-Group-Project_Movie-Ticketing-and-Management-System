package release.movie;

import release.helper.Time;

public class MovieSession implements Comparable<MovieSession>{
	private Movie movie;
	private String startTime;
    private String endTime;
	private House house;
	private SeatingPlan seats;
	
		
	public MovieSession(Movie movie, String startTime, String endTime, House house) {
		this.movie = movie;
		this.startTime = startTime;
		this.endTime = endTime;
		this.house = house;
		this.seats = house.getNewSeatingPlanForNewMovieSession();
	}
	
	public Movie getMovie() {return movie;}
	
	public String getStartTime() {return startTime;}
	
	public String getEndTime() {return endTime;}
	
	public House getHouse() {return house;}
	
	public SeatingPlan getSeats() {return seats;}
	
	@Override
	public int compareTo(MovieSession other) {
        int thisStartTime = Time.convertTimeToMins(this.startTime);
        int otherStartTime = Time.convertTimeToMins(other.getStartTime());
        return Integer.compare(thisStartTime, otherStartTime);
    }
	
	public String displayBasicInfo() {
		return "House: " + house.getHouseNumber() + ", Start Time: " + startTime + ", End Time: " + endTime;
	}
	
	@Override
	public String toString() {
		return "Movie: " + movie.getName() + ", House: " + house.getHouseNumber() + ", Start Time: " + startTime + ", End Time: " + endTime;
	}

	
}
