package release.exception;

public class ExInvalidPrice extends Exception {
    public ExInvalidPrice() {
        super("[Exception] Movie price must be between 1 and 500.");
    }
    public ExInvalidPrice(String message) {
        super("[Exception] " + message);
    }
}
