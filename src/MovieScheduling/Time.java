package MovieScheduling;

public class Time {
	public static String convertMinsToTimeString(int minutesSinceMidnight) {
        int hours = minutesSinceMidnight / 60;
        int minutes = minutesSinceMidnight % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
	
	public static int convertTimeToMins(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
	
	public static int getHourFromTimeString(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]);
    }
}
