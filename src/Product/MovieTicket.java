package Product;

import selectMovieModule.Movie;
import selectMovieModule.MovieSession;

public class MovieTicket implements Product {
    /**
     * Constructor for MovieTicket class
     */
    private final Movie movie;
    private final MovieSession movieSession;
    String seat;

    public MovieTicket(Movie movie, MovieSession movieSession, String seat) {
        this.movie = movie;
        this.movieSession = movieSession;
        this.seat = seat;
    }

    /**
     * get the movie name of the ticket
     *
     * @return movie name
     */
    @Override
    public String getName() {
        return movie.getName();
    }

    /**
     * get the price of the movie ticket
     *
     * @return price of the movie ticket
     */
    @Override
    public double getPrice() {
        return movie.getPrice();
    }

    /**
     * get the movie of the movie ticket
     *
     * @return movie of the movie ticket
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * get the movie session of the movie ticket
     *
     * @return movie session of the movie ticket
     */
    public MovieSession getMovieSession() {
        return movieSession;
    }

    /**
     * get the seat of the movie ticket
     */
    public String getSeat() {
        return seat;
    }

    /**
     * set the seat of the movie ticket
     *
     * @param seat new seat of the movie ticket
     * @return the seat of the movie ticket set
     */
    public String setSeat(String seat) {
        this.seat = seat;
        return this.seat;
    }

}
