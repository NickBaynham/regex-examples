package io.nickbaynham.automation.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static boolean isMatch(String target, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static int getNumberOfMatches(String target, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public static int getNumberOfMatches(String target, String regex, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(target);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public static String replaceAll(String target, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.replaceAll(replacement);
    }

    public static String replaceFirst(String target, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.replaceFirst(replacement);
    }

}
