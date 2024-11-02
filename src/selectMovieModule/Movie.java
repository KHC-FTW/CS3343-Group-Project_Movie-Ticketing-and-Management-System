package selectMovieModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Movie{
	private String name;
	private String genre;
	private int duration;	// mins
	private double price;
	private String classification;	// I, IIA, IIB, III
	private String language;
	private String subtitles;
	private List<MovieSession> movieSessions;
	
	//default constructor, placeholder for testing purposes
	public Movie() {
		this("name", "genre", 0, 0, "class", "language", "subtitles");
	}
	
	public Movie(String name, 
				 String genre, 
				 int duration, 
				 double price,
				 String classification, 
				 String language, 
				 String subtitles) {
		this.name = name;
		this.genre = genre;
		this.duration = duration;
		this.price = price;
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
	public String getClassification() {return classification;}
	public String getLanguage() {return language;}
	public String getSubtitles() {return subtitles;}
	public List<MovieSession> getMovieSessionList() {return movieSessions;}

	public static String displayMovieInfoHeaderForCustomer() {
		StringBuilder result = new StringBuilder(String.format("%-5s", " "));
		result.append(String.format("%-30s%-2s", "Movie", " "));
		result.append(String.format("%-4s%-6s", "Duration", " "));
		result.append(String.format("%-12s", "Genre"));
		result.append(String.format("%-9s", "Price"));
		result.append(String.format("%-8s", "Class"));
		result.append(String.format("%-12s", "Language"));
		result.append(String.format("%-12s", "Subtitles"));
		result.append("\n");
		result.append("------------------------------------------------------------------------------------------------------\n");
		return result.toString();
	}

	public String displayMovieInfoForCustomer(int num) {
		StringBuilder result = new StringBuilder(String.format("%3d)%-1s", num, " "));
		result.append(String.format("%-30s%-2s", name, " "));
        result.append(String.format("%-4dmins%-6s", duration, " "));
        result.append(String.format("%-12s", genre));
        result.append(String.format("$%-8.0f", price));
        result.append(String.format("%-8s", classification));
        result.append(String.format("%-12s", language));
        result.append(String.format("%-12s", subtitles));
        result.append("\n");
        return result.toString();
	}

	public static String displayMovieInfoHeaderForAdmin(){
		StringBuilder result = new StringBuilder(String.format("%-5s", " "));
		result.append(String.format("%-30s%-2s", "Movie", " "));
		result.append(String.format("%-4s%-6s", "Duration", " "));
		result.append(String.format("%-12s", "Genre"));
		result.append(String.format("%-9s", "Price"));
		result.append(String.format("%-8s", "Class"));
		result.append(String.format("%-12s", "Language"));
		result.append(String.format("%-12s", "Subtitles"));
		result.append(String.format("%-10s", "Scheduled?"));
		result.append("\n");
		result.append("--------------------------------------------------------------------------------------------------------------------\n");
		return result.toString();
	}

	public String displayMovieInfoForAdmin(int num){
		StringBuilder result = new StringBuilder(String.format("%3d)%-1s", num, " "));
		result.append(String.format("%-30s%-2s", name, " "));
		result.append(String.format("%-4dmins%-6s", duration, " "));
		result.append(String.format("%-12s", genre));
		result.append(String.format("$%-8.0f", price));
		result.append(String.format("%-8s", classification));
		result.append(String.format("%-12s", language));
		result.append(String.format("%-12s", subtitles));
		if (this.movieSessions == null) result.append(String.format("%-10s", "No"));
		else result.append(String.format("%-10s", "Yes"));
		result.append("\n");
		return result.toString();
	}

	public boolean customerAllowToWatch(Customer customer){
		if (getClassification().equals("III"))
			return customer.getState().canViewClassIIIMovies();
		return true;
	}

	public static String displayMovieOptions(Map<String, Movie> queriedOptions){
		StringBuilder result = new StringBuilder("The following movies are currently ticketing: \n");
		Set<String> keys = queriedOptions.keySet();
		for (String key : keys) {
			result.append(String.format("%2s)%1s", key, " ")).append(queriedOptions.get(key).getName()).append("\n");
		}
		return result.toString();
	}
	
	@Override
	public boolean equals(Object movie) {
		return this.name.equals(((Movie)movie).getName()) 
				&& this.genre.equals(((Movie)movie).getGenre()) 
				&& this.duration == ((Movie)movie).getDuration() 
				&& this.price == ((Movie)movie).getPrice() 
				&& this.classification.equals(((Movie)movie).getClassification()) 
				&& this.language.equals(((Movie)movie).getLanguage()) 
				&& this.subtitles.equals(((Movie)movie).getSubtitles());
	}
}
