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
	
	public RegisteredUser register(String userName, String password, int age) throws CustomException {
		if (cinemaDatabase.getRegisteredUsers().containsKey(userName)) {
			throw new CustomException("User with the name \"" + userName + "\" already exists.");
		}
		RegisteredUser newUser =  new Customer(userName, password, age);
		cinemaDatabase.addRegisteredUser(newUser);
		return newUser;
	}
	
	public RegisteredUser login(String userName, String password) throws CustomException {
		RegisteredUser user = cinemaDatabase.findUser(userName);
		if (!user.getPassword().equals(password)) {
			throw new CustomException("Log in failed. Incorrect password.");
		}
		return user;
	}
	
}
