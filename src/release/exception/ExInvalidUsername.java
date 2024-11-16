package release.exception;

public class ExInvalidUsername extends Exception {
    public ExInvalidUsername() { super("[Exception] Invalid username! Username should contain no space and between 1 to 12 characters."); }
    public ExInvalidUsername(String msg) { super(msg); }
}