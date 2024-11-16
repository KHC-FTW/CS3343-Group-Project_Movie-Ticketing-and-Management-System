package release.exception;

public class ExNoMovieToShow extends Exception{
    public ExNoMovieToShow(){super("[Exception] There are no movies to show.");}
    public ExNoMovieToShow(String exMsg){super("[Exception] " + exMsg);}
}
