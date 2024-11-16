package release.exception;

public class ExInvalidGenre extends Exception {
    public ExInvalidGenre() {
        super("[Exception] Invalid genre. Valid genres include Action, Thriller, Comedy, Romance, Horror, Family, Cartoon, Sci-fi, Drama, Documentary, Fantasy, Adventure.");
    }
}
