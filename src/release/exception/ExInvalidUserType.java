package release.exception;

public class ExInvalidUserType extends Exception {
    public ExInvalidUserType() { super("[Exception] Invalid user type! The valid type are \"Member\" or \"Admin\"."); }
    public ExInvalidUserType(String msg) { super(msg); }
}
