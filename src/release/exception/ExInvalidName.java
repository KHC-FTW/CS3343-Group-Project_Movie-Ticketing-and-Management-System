package release.exception;

public class ExInvalidName extends Exception {
    public ExInvalidName() { super("[Exception] Invalid name! Name should be between 1 to 12 characters."); }
    public ExInvalidName(String msg) { super(msg); }
}