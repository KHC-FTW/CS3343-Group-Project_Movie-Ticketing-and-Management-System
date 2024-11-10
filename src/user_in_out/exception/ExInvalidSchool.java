package user_in_out.exception;

public class ExInvalidSchool extends Exception {
    public ExInvalidSchool() { super("[Exception] Invalid school name!"); }
    public ExInvalidSchool(String msg) { super(msg); }
}
