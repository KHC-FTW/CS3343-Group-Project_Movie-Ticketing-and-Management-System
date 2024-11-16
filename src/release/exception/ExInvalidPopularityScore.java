package release.exception;

public class ExInvalidPopularityScore extends Exception {
    public ExInvalidPopularityScore() {
        super("[Exception] Popularity score must be a number between 0 and 10.");
    }
    public ExInvalidPopularityScore(String message) {
        super("[Exception] " + message);
    }
}
