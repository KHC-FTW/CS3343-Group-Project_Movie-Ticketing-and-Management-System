package user_in_out.movie;

public class MovieCenter {
    public static MovieCenter instance = new MovieCenter();

    public static MovieCenter getInstance() { return instance; }

    public void listMovies() { 
        System.out.println("Movies:\n" + 
        "1. Kill Bill\n" +
        "2. Pacific Rim\n" +
        "3. Harry Potter");
    }

    public void listMoviesAdmin() { 
        System.out.println("Movies:\n" + 
        "1. Kill Bill    $200\n" +
        "2. Pacific Rim  $300\n" +
        "3. Harry Potter $290");
    }
}