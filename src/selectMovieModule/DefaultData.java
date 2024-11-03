/*
 * Some hardcoded data as default
 */
package selectMovieModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultData {
	private static String openHours = "11:00";
	private static String closeHours = "24:00";
	private static List<House> houses = new ArrayList<>(4);
	private static List<Movie> movies = Arrays.asList(
										new Movie("The Matrix", "Action", 120, 50, 8.0, "IIB", "English", "Chinese"),
										new Movie("The Matrix Reloaded", "Action", 130, 50, 7.5, "IIB", "English", "Chinese"),
										new Movie("The Matrix Revolutions", "Action", 140, 50, 7, "IIB", "English", "Chinese"),
										new Movie("The Matrix Resurrections", "Action", 150, 40, 5.5, "IIB", "English", "Chinese"),
										new Movie("The Avengers", "Action", 180, 100, 8, "IIA", "English", "Chinese"),
										new Movie("Avengers: Age of Ultron", "Action", 190, 55, 7.8, "IIA", "English", "Chinese"),
										new Movie("Avengers: Infinity War", "Action", 200, 55, 8.2, "IIA", "English", "Chinese"),
										new Movie("Avengers: Endgame", "Action", 210, 60, 8.5, "IIA", "English", "Chinese"),
										new Movie("Deadpool", "Comedy", 150, 50, 7.8, "III", "English", "Chinese"),
										new Movie("Deadpool 2", "Comedy", 160, 55, 7.2, "III", "English", "Chinese"),
										new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese"),
										new Movie("Batman Begins", "Thriller", 150, 45, 7.1, "IIA", "English", "Chinese"),
										new Movie("The Dark Knight", "Thriller", 180, 45, 8.8, "IIA", "English", "Chinese"),
										new Movie("The Dark Knight Rises", "Thriller", 180, 45, 8, "IIA", "English", "Chinese"));
	
	private static Map<String, RegisteredUser> registeredUsers = Map.of(
												Admin.getInstance().getUserName(), Admin.getInstance(),
												Customer.getDummyChildrenCustomer().getUserName(), Customer.getDummyChildrenCustomer(),
												Customer.getDummyAdultCustomer().getUserName(), Customer.getDummyAdultCustomer(),
												Customer.getDummyElderlyCustomer().getUserName(), Customer.getDummyElderlyCustomer());
	
	public static void loadDefaultDataToDB() {
		try {
			houses.add(new House(1, 10, 5));
			houses.add(new House(2, 8, 6));
			houses.add(new House(3, 8, 10));
			houses.add(new House(4, 12, 15));
			houses.add(new House(5, 20, 20));
		}catch(CustomException e) {}
        CinemaDatabase db = CinemaDatabase.getInstance();
        db.setOpeningHours(openHours);
        db.setClosingHours(closeHours);
        db.setHouses(houses);
        db.setMovies(movies);
        db.setRegisteredUsers(registeredUsers);
    }
}
