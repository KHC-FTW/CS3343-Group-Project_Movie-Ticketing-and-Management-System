package test.helper_movie_test;

import org.junit.jupiter.api.Test;
import release.exception.*;
import release.helper.FormatChecker;

import static org.junit.jupiter.api.Assertions.*;

public class FormatCheckerTest {

    @Test
    public void testCheckInputDynamic() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkInputDynamic("validInput", "type", 12));
        assertThrows(ExInvalidInputLength.class, () -> fc.checkInputDynamic("", "type", 12));
        assertThrows(ExInvalidInputLength.class, () -> fc.checkInputDynamic("tooLongInputString", "type", 12));
    }

    @Test
    public void testCheckDuration() throws ExInvalidDuration {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkDuration("120"));
        assertEquals(120, fc.checkDuration("120"));
        assertThrows(ExInvalidDuration.class, () -> fc.checkDuration("0"));
        assertThrows(ExInvalidDuration.class, () -> fc.checkDuration("301"));
        assertThrows(ExInvalidDuration.class, () -> fc.checkDuration("invalid"));
    }

    @Test
    public void testCheckGenre() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkGenre("Action"));
        assertDoesNotThrow(() -> fc.checkGenre("Thriller"));
        assertDoesNotThrow(() -> fc.checkGenre("Comedy"));
        assertDoesNotThrow(() -> fc.checkGenre("Romance"));
        assertDoesNotThrow(() -> fc.checkGenre("Horror"));
        assertDoesNotThrow(() -> fc.checkGenre("Family"));
        assertDoesNotThrow(() -> fc.checkGenre("Cartoon"));
        assertDoesNotThrow(() -> fc.checkGenre("Sci-fi"));
        assertDoesNotThrow(() -> fc.checkGenre("Drama"));
        assertDoesNotThrow(() -> fc.checkGenre("Fantasy"));
        assertDoesNotThrow(() -> fc.checkGenre("Adventure"));
        assertThrows(ExInvalidGenre.class, () -> fc.checkGenre("InvalidGenre"));
    }

    @Test
    public void testCheckPrice() throws ExInvalidPrice {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkPrice("100.0"));
        assertEquals(100.0, fc.checkPrice("100.0"));
        assertThrows(ExInvalidPrice.class, () -> fc.checkPrice("-1"));
        assertThrows(ExInvalidPrice.class, () -> fc.checkPrice("501"));
        assertThrows(ExInvalidPrice.class, () -> fc.checkPrice("invalid"));
    }

    @Test
    public void testCheckPopularityScore() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkPopularityScore("8.5"));
        assertThrows(ExInvalidPopularityScore.class, () -> fc.checkPopularityScore("-1"));
        assertThrows(ExInvalidPopularityScore.class, () -> fc.checkPopularityScore("11"));
        assertThrows(ExInvalidPopularityScore.class, () -> fc.checkPopularityScore("invalid"));
    }

    @Test
    public void testCheckClassification() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkClassification("I"));
        assertDoesNotThrow(() -> fc.checkClassification("IIA"));
        assertDoesNotThrow(() -> fc.checkClassification("IIB"));
        assertDoesNotThrow(() -> fc.checkClassification("III"));
        assertThrows(ExInvalidClassification.class, () -> fc.checkClassification("InvalidClass"));
    }

    @Test
    public void testCheckLanguage() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkLanguage("English"));
        assertDoesNotThrow(() -> fc.checkLanguage("Cantonese"));
        assertDoesNotThrow(() -> fc.checkLanguage("Putonghua"));
        assertDoesNotThrow(() -> fc.checkLanguage("Japanese"));
        assertDoesNotThrow(() -> fc.checkLanguage("Korean"));
        assertDoesNotThrow(() -> fc.checkLanguage("French"));
        assertDoesNotThrow(() -> fc.checkLanguage("Spanish"));
        assertThrows(ExInvalidLanguage.class, () -> fc.checkLanguage("InvalidLanguage"));
    }

    @Test
    public void testCheckSubtitles() {
        FormatChecker fc = FormatChecker.getInstance();
        assertDoesNotThrow(() -> fc.checkSubtitles("Chinese"));
        assertDoesNotThrow(() -> fc.checkSubtitles("English"));
        assertThrows(ExInvalidSubtitles.class, () -> fc.checkSubtitles("InvalidSubtitles"));
    }
}