package release.exception;

public class ExUserNotExist extends Exception {
    public ExUserNotExist() { super("[Exception] User does not exist!"); }
    public ExUserNotExist(String msg) { super(msg); }
}