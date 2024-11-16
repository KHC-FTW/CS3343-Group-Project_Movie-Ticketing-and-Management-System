package release.exception;

public class ExInvalidSeat extends Exception{
    public ExInvalidSeat(String message) {
        super("[Exception] Invalid format for \"" + message + "\".");
    }
}
