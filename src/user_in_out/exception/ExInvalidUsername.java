package user_in_out.exception;

public class ExInvalidUsername extends Exception {
    public ExInvalidUsername() { super("[Exception] Invalid username! Username should contain no space and within 12 characters."); }
    public ExInvalidUsername(String msg) { super(msg); }
}