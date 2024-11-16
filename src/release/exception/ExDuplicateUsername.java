package release.exception;

public class ExDuplicateUsername extends Exception {
    public ExDuplicateUsername() { super("[Exception] This username is already occupied by others!"); }
    public ExDuplicateUsername(String msg) { super(msg); }
}