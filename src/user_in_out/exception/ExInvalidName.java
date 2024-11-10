package user_in_out.exception;

public class ExInvalidName extends Exception {
    public ExInvalidName() { super("[Exception] Invalid name! Name should not exceeds 12 characters."); }
    public ExInvalidName(String msg) { super(msg); }
}