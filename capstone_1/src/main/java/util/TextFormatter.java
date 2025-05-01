package util;

public class TextFormatter {
    public static String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() <= maxLength ? text : text.substring(0, maxLength - 3) + "...";
    }

    public static String formatTime(String timeString) {
        if (timeString == null || timeString.length() < 8) {
            return "";
        }
        return timeString.substring(0, 8);
    }

}