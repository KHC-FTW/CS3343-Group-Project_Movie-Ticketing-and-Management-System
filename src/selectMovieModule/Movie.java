package selectMovieModule;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Comparable<Movie>{
	private String name;
	private String genre;
	private int duration;	// mins
	private double price;
	private double popularityScore;
	private String classification;	// I, IIA, IIB, III
	private String language;
	private String subtitles;
	private List<MovieSession> movieSessions;
	
	//default constructor, placeholder for testing purposes
	public Movie() {
		this("name", "genre", 0, 0, 0, "class", "language", "subtitles");
	}
	
	public Movie(String name, 
				 String genre, 
				 int duration, 
				 double price,
				 double popularityScore,
				 String classification, 
				 String language, 
				 String subtitles) {
		this.name = name;
		this.genre = genre;
		this.duration = duration;
		this.price = price;
		this.popularityScore = popularityScore;
		this.classification = classification;
		this.language = language;
		this.subtitles = subtitles;
	}
	
	public List<MovieSession> addMovieSession(MovieSession movieSession) {
        if (movieSessions == null) {movieSessions = new ArrayList<>();}
        movieSessions.add(movieSession);
        return movieSessions;
	}
	
	public String getName() {return name;}
	public String getGenre() {return genre;}
	public int getDuration() {return duration;}
	public double getPrice() {return price;}
	public double getPopularityScore() {return popularityScore;}
	public String getClassification() {return classification;}
	public String getLanguage() {return language;}
	public String getSubtitles() {return subtitles;}
	public List<MovieSession> getMovieSessionList() {return movieSessions;}

	public boolean customerAllowToWatch(Customer customer){
		if (getClassification().equals("III"))
			return customer.getState().canViewClassIIIMovies();
		return true;
	}
	
	@Override
	public boolean equals(Object movie) {
		return this.name.equals(((Movie)movie).getName()) 
				&& this.genre.equals(((Movie)movie).getGenre()) 
				&& this.duration == ((Movie)movie).getDuration() 
				&& this.price == ((Movie)movie).getPrice() 
				&& this.popularityScore == ((Movie)movie).getPopularityScore()
				&& this.classification.equals(((Movie)movie).getClassification()) 
				&& this.language.equals(((Movie)movie).getLanguage()) 
				&& this.subtitles.equals(((Movie)movie).getSubtitles());
	}

	@Override
	public int compareTo(Movie other) {
		double thisScore = this.popularityScore * this.price;
        double otherScore = other.popularityScore * other.price;
        return Double.compare(otherScore, thisScore);
	}
}
