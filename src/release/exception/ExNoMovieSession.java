package release.exception;

public class ExNoMovieSession extends Exception{
    public ExNoMovieSession(){super("[Exception] There are no movie sessions to show.");}
    public ExNoMovieSession(String exMsg){super("[Exception] " + exMsg);}
}
