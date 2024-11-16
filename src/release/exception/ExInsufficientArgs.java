package release.exception;

public class ExInsufficientArgs extends Exception {
    public ExInsufficientArgs() { super("[Exception] Insufficient Arguments"); }
    public ExInsufficientArgs(String msg) { super(msg); }
}