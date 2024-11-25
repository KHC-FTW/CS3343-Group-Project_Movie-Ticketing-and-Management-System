package test.helper_movie_test;

import release.helper.Time;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    public void testConvertMinsToTimeString() {
        assertEquals("00:00", Time.convertMinsToTimeString(0));
        assertEquals("01:00", Time.convertMinsToTimeString(60));
        assertEquals("12:30", Time.convertMinsToTimeString(750));
        assertEquals("23:59", Time.convertMinsToTimeString(1439));
    }

    @Test
    public void testConvertTimeToMins() {
        assertEquals(0, Time.convertTimeToMins("00:00"));
        assertEquals(60, Time.convertTimeToMins("01:00"));
        assertEquals(750, Time.convertTimeToMins("12:30"));
        assertEquals(1439, Time.convertTimeToMins("23:59"));
    }

    @Test
    public void testGetHourFromTimeString() {
        assertEquals(0, Time.getHourFromTimeString("00:00"));
        assertEquals(1, Time.getHourFromTimeString("01:00"));
        assertEquals(12, Time.getHourFromTimeString("12:30"));
        assertEquals(23, Time.getHourFromTimeString("23:59"));
    }
}
