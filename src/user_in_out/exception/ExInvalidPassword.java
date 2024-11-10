package user_in_out.exception;

public class ExInvalidPassword extends Exception {
    public ExInvalidPassword() { super("[Exception] Invalid password! Password should contain no space and within 15 characters."); }
    public ExInvalidPassword(String msg) { super(msg); }
}
