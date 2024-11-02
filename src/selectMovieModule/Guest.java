package selectMovieModule;

import java.util.Map;

public class Guest extends User{
	
	private Guest() {}
	private static final Guest instance = new Guest();
	public static Guest getInstance() {return instance;}
	
	@Override
	public Map<Integer, Movie> listMovies() throws CustomException {
		return super.listMovies();
	}
	
	@Override
	public Map<Integer, Movie> searchMovies(String name) throws CustomException {
		return super.searchMovies(name);
	}
	
	public User register(String userName, String password, int age) throws CustomException {
		return new Customer(userName, password, age);
	}
	
	public User login(String userName, String password) throws CustomException {
		return new RegisteredUser(userName, password);
	}
	
}
