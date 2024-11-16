package release.exception;

public class ExInvalidClassification extends Exception {
    public ExInvalidClassification() {
        super("[Exception] Movie classification must be either I, IIA, IIB, or III.");
    }
    public ExInvalidClassification(String message) {
        super("[Exception] " + message);
    }
}
