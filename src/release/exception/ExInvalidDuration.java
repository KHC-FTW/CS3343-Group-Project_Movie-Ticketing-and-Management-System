package release.exception;

public class ExInvalidDuration extends Exception {
    public ExInvalidDuration() {
        super("[Exception] Movie duration must be between 1 and 300 minutes.");
    }
    public ExInvalidDuration(String message) {
        super("[Exception] " + message);
    }
}
