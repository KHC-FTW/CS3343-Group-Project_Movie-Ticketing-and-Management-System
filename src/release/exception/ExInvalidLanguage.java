package release.exception;

public class ExInvalidLanguage extends Exception {
    public ExInvalidLanguage() {
        super("[Exception] Currently provided movie languages only include English, Cantonese, Putonghua, Japanese, Korean, French, and Spanish.");
    }
    public ExInvalidLanguage(String message) {
        super("[Exception] " + message);
    }
}
