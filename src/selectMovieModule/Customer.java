package selectMovieModule;

import java.util.*;

public class Customer extends RegisteredUser{
	private CustomerState state;
	private int age;
	private List<String> cachedSelectedSeats;
	private MovieSession cachedSelectedMovieSession;
	private final BookSeatService bookSeatService = BookSeatService.getInstance();

	public static CustomerState customerStateForAge(int age) throws CustomException {
		if (age >= 0 && age < 18) return Children.getInstance();
		else if (age >= 18 && age < 65) return Adult.getInstance();
		else if (age >= 65) return Elderly.getInstance();
		else throw new CustomException("Invalid age.");
	}
	
	public Customer(String userName, String password, int age) throws CustomException {
		super(userName, password);
		this.state = customerStateForAge(age);
		this.age = age;
	}
	
	public int getAge(){return age;}
    public CustomerState getState() {return state;}
	
	// dummy for children customer
	public static Customer getDummyChildrenCustomer() {
        try {
            return new Customer("testChildren", "password", 10);
        } catch (CustomException e) {
			return null;
        }
    }
	
	// dummy for adult customer
	public static Customer getDummyAdultCustomer() {
        try {
            return new Customer("testAdult", "password", 20);
        } catch (CustomException e) {
            return null;
        }
    }
	
	// dummy for elderly customer
	public static Customer getDummyElderlyCustomer() {
        try {
            return new Customer("testElderly", "password", 70);
        } catch (CustomException e) {
            return null;
        }
    }

	@Override
	public Map<Integer, Movie> listMovies() throws CustomException {
		return super.listMovies();
	}
	
	@Override
	public Map<Integer, Movie> searchMovies(String name) throws CustomException {
		return super.searchMovies(name);
	}
	
	public Movie getSelectedMovie(Map<Integer, Movie> movies, int selected) throws CustomException {
		return super.movieService.getSelectedMovie(movies, selected);
	}
	
	public Map<Integer, MovieSession> getMovieSessionsFromMovie(Movie movie) throws CustomException {
		return super.movieService.getMovieSessions(movie);
	}
	
	public MovieSession getSelectedMovieSession(Map<Integer, MovieSession> movieSessions, int selected)
			throws CustomException {
		return super.movieService.getSelectedMovieSession(movieSessions, selected);
	}
	
	public boolean bookSeat(SeatingPlan seatingPlan, String option) throws CustomException {
		return bookSeatService.bookSeat(seatingPlan, option);
	}
	
	public boolean undoBookSeat(SeatingPlan seatingPlan, String option) throws CustomException {
		return bookSeatService.releaseSeat(seatingPlan, option);
	}

	public boolean cacheSelectedSeat(String seat) {
		if (cachedSelectedSeats == null) cachedSelectedSeats = new ArrayList<>();
		// make sure no duplicate seat will be added
		if (cachedSelectedSeats.contains(seat)) return false;
		else{
			cachedSelectedSeats.add(seat);
			return true;
		}
	}

	// Limit only one movie session to be cached
	// if want to cache another session, must first process the first, clear it, before caching another session
	public boolean cacheMovieSession(MovieSession movieSession) {
		if (this.cachedSelectedMovieSession == null){
			this.cachedSelectedMovieSession = movieSession;
			return true;
		}else return false;
	}
	
	public List<String> getCachedSeats() throws CustomException {
		if (cachedSelectedSeats == null) throw new CustomException("There are no cached selected seats.");
		return cachedSelectedSeats;
	}

	public MovieSession getCachedMovieSession() throws CustomException {
		if (cachedSelectedMovieSession == null) throw new CustomException("There are no cached movie session.");
		return cachedSelectedMovieSession;
	}

	// avoid repeatedly calling this function when the cache is already null
	public boolean clearCachedSeats() {
		if (cachedSelectedSeats != null) {
			cachedSelectedSeats = null;
			return true;
		}else return false;
	}

	// avoid repeatedly calling this function when the cache is already null
	public boolean clearCachedMovieSession() {
		if (cachedSelectedMovieSession != null){
			cachedSelectedMovieSession = null;
			return true;
		}else return false;
	}

	@Override
	public String toString() {
		return "Customer: " + super.getUserName() + ", state: " + state.toString() + ", age: " + age;
	}

}
