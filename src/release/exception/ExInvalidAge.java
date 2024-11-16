package release.exception;

public class ExInvalidAge extends Exception {
    public ExInvalidAge() { super("[Exception] Age should be a number and between 1 and 125!"); }
    public ExInvalidAge(String msg) { super(msg); }
}