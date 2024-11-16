package release.exception;

public class ExInvalidSubtitles extends Exception {
    public ExInvalidSubtitles() {
        super("[Exception] Currently provided movie subtitles only include Chinese or English.");
    }
    public ExInvalidSubtitles(String message) {
        super("[Exception] " + message);
    }
}
