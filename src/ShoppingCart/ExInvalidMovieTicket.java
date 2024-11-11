package ShoppingCart;

/**
 * ExInvalidMovieTicket class
 * It is used to represent the exception when the movie ticket is invalid
 */
public class ExInvalidMovieTicket extends Exception {
  public ExInvalidMovieTicket() {
        super("[Exception] Invalid movie ticket");
    }
}
