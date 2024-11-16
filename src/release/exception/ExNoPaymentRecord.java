package release.exception;

public class ExNoPaymentRecord extends Exception {
    public ExNoPaymentRecord() {
        super("[Exception] There is no payment record to show.");
    }
}
