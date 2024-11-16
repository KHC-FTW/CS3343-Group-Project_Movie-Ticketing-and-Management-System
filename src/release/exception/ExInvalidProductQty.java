package release.exception;

public class ExInvalidProductQty extends Exception{
    public ExInvalidProductQty(){super("[Exception] Input quantity should be at least 1 and not more then 10.");}
}
