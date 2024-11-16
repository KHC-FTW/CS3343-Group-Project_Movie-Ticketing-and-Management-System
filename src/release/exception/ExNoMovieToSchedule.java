package release.exception;

public class ExNoMovieToSchedule extends Exception{
    public ExNoMovieToSchedule() { super("[Exception] The database has no movie to schedule. Please add movie first."); }
    public ExNoMovieToSchedule(String msg) { super(msg); }
}
