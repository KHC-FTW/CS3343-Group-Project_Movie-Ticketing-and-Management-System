package release.exception;

public class ExDeleteSelf extends Exception {
    public ExDeleteSelf() { super("[Exception] Self-delet is not allowed!"); }
    public ExDeleteSelf(String msg) { super(msg); }
}