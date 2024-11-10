package user_in_out.exception;

public class ExInsufficientArgs extends Exception {
    public ExInsufficientArgs() { super("[Exception] Insuffucuent Arguments"); }
    public ExInsufficientArgs(String msg) { super(msg); }
}