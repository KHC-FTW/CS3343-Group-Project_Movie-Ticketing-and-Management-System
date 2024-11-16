package release.exception;

public class ExInvalidInputLength extends Exception {
    public ExInvalidInputLength(String type, int errMsg) {
        super("[Exception] The " + type + " should be 1 to " + errMsg + " characters long.");
    }
}
