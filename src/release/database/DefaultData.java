package release.database;

import release.exception.CustomException;
import release.externalAPI.ExternalAPI;
import release.externalAPI.OctopusAPI;
import release.movie.House;
import release.movie.Movie;
import release.product.Drink;
import release.product.ProductWithPortion;
import release.product.Snack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultData {
    private DefaultData(){}
    private static final DefaultData instance = new DefaultData();
    public static DefaultData getInstance(){return instance;}

    private boolean hasLoadedMovies = false;
    private final Database db = Database.getInstance();
    private final String openHours = "10:00";
    private final String closeHours = "24:00";
    private final List<House> houses = new ArrayList<>(5);
    private final List<Movie> movies = new ArrayList<>(Arrays.asList(
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
            new Movie("The Dark Knight Rises", "Thriller", 180, 45, 8, "IIA", "English", "Chinese"))
    );

    private final List<ProductWithPortion> snacksDrinks = new ArrayList<>(Arrays.asList(
            new Snack("Caramel Popcorn", 45.9, "Regular"),
            new Snack("Butter Popcorn", 51, "Regular"),
            new Snack("Chocolate Popcorn", 51.3, "Regular"),
            new Drink("CocaCola", 18.9, "330 ml"),
            new Drink("Pepsi", 17.8, "330 ml"),
            new Drink("Orange Juice", 23.1, "250 ml"))
    );


    public void setUpDB() {
        try {
            houses.add(new House(1, 20, 20));
            houses.add(new House(2, 12, 15));
            houses.add(new House(3, 10, 11));
            houses.add(new House(4, 8, 10));
            houses.add(new House(5, 6, 10));
        }catch(CustomException e) {}
        db.setOpeningHours(openHours);
        db.setClosingHours(closeHours);
        db.setHouses(houses);
        db.setProducts(snacksDrinks);
//        db.setMovies(movies);
    }

    public List<Movie> loadDefaultMoviesToDB(){
        db.setMovies(movies);
        setHasLoadedMovies();
        return movies;
    }

    private boolean setHasLoadedMovies(){
        return hasLoadedMovies = true;
    }

    public boolean getMoviesLoadedStatus(){return hasLoadedMovies;}

    public List<Movie> getDefaultMovies(){
        return movies;
    }
}
