package release.exception;

public class ExInvalidPaymentOption extends Exception {
    public ExInvalidPaymentOption(){super("[Exception] Selected payment option is invalid.");}
    public ExInvalidPaymentOption(String exMsg){super("[Exception] " + exMsg);}
}
