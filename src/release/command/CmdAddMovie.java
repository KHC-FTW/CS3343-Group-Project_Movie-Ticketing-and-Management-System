package release.command;

import java.util.*;

import release.database.Database;
import release.exception.*;
import release.helper.FormatChecker;
import release.movie.Movie;
import release.movie.MovieInfoToString;

public class CmdAddMovie implements Command {
    Database db = Database.getInstance();
    FormatChecker fc = FormatChecker.getInstance();

    Map<Integer, Movie> newMovies = new LinkedHashMap<>();
    int count = 0;

//    List<Movie> newMovies = new ArrayList<>();
    String input = "";

    @Override
    public void execute(Scanner scanner) {
        System.out.println("[Remind] You may leave the process at any time by entering \"q\" or \"quit\".\n");

        System.out.print("Enter the movie data in this format:\n" +
                "[name], [genre], [duration], [price], [popularityScore], [classification], [language], [subtitles]\n");

        while(true) {
            try {
                System.out.print("> ");

                input = scanner.nextLine().strip();
                System.out.println();

                if (input.equals("q") || input.equals("quit"))
                    break;

                String[] parts = Arrays.stream(input.split(","))
                        .map(String::strip)
                        .toArray(String[]::new);

//                if (parts.length != 8)
//                    throw new ExInsufficientArgs("[Exception] 8 values must be provided.");

//                String name = parts[0];
//                String genre = fc.checkGenre(parts[1]);
//                int duration = fc.checkDuration(parts[2]);
//                double price = fc.checkPrice(parts[3]);
//                double rating = fc.checkPopularityScore(parts[4]);
//                String classification = fc.checkClassification(parts[5]);
//                String language = fc.checkLanguage(parts[6]);
//                String subtitles = fc.checkSubtitles(parts[7]);

                if (isValidMovieData(parts)){
                    Movie movie = createNewMovie(parts);
                    if(isDuplicate(newMovies, movie) || isDuplicate(db.getMovies(), movie)){
                        System.out.println("[Exception] Duplicate movie detected. Failed to add. Please try again.\n");
                        continue;
                    }
                    newMovies.put(++count, movie);
                    db.addMovie(movie);
                    System.out.println("[State] Successfully added one movie!\n");
                }
            } catch (ExInsufficientArgs e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        if (newMovies.isEmpty())
            System.out.println("[State] Add movie process terminated!\n");
        else {
            System.out.println("All newly added movies:");
            try {
                System.out.println(MovieInfoToString.showMovieAdmin(newMovies));
            } catch (ExNoMovieToShow ignored) {}
        }
    }

    private Movie createNewMovie(String[] parts) {
        String name = parts[0];
        String genre = parts[1];
        int duration = Integer.parseInt(parts[2]);
        double price = Double.parseDouble(parts[3]);
        double rating = Double.parseDouble(parts[4]);
        String classification = parts[5];
        String language = parts[6];
        String subtitles = parts[7];
        return new Movie(name, genre, duration, price, rating, classification, language, subtitles);
    }

    private boolean isDuplicate(Map<Integer, Movie> addedMovies, Movie newMovie) {
        if(addedMovies.isEmpty())
            return false;
        for (Movie movie: addedMovies.values()){
            if (movie.equals(newMovie))
                return true;
        }
        return false;
    }

    private boolean isDuplicate(List<Movie> addedMovies, Movie newMovie) {
        if(addedMovies.isEmpty())
            return false;
        for (Movie movie: addedMovies){
            if (movie.equals(newMovie))
                return true;
        }
        return false;
    }

    private boolean isValidMovieData(String[] parts) throws ExInsufficientArgs {
        if (parts.length != 8)
            throw new ExInsufficientArgs("[Exception] 8 values must be provided.");
        boolean isValidData = true;
        // the reason for using multiple try-catch blocks instead of
        // one single try-catch block is to show all the exception messages at once
        // otherwise only the first caught exception will be shown
        try{
            fc.checkInputDynamic(parts[0], "movie name", 30);
        } catch (ExInvalidInputLength e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkGenre(parts[1]);
        } catch (ExInvalidGenre e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkDuration(parts[2]);
        } catch (ExInvalidDuration e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkPrice(parts[3]);
        } catch (ExInvalidPrice e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkPopularityScore(parts[4]);
        } catch (ExInvalidPopularityScore e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkClassification(parts[5]);
        } catch (ExInvalidClassification e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkLanguage(parts[6]);
        } catch (ExInvalidLanguage e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        try {
            fc.checkSubtitles(parts[7]);
        } catch (ExInvalidSubtitles e) {
            System.out.println(e.getMessage() + "\n");
            isValidData = false;
        }
        return isValidData;
    }
}