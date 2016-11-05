package net.sadovnikov.mbf4j.activities;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity {

    protected String type;

    public boolean isMessage() {
        Pattern regexp = Pattern.compile("^message[/.*]?$");
        Matcher matcher = regexp.matcher(type);
        return matcher.matches();
    }
}
