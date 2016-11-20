package net.sadovnikov.mbf4j.activities;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity {

    protected String type;

    public final String TYPE_MESSAGE = "message";
    public final String TYPE_CONTACT_RELATION_UPDATE = "contactRelationUpdate";
    public final String TYPE_CONVERSATION_UPDATE = "conversationUpdate";

    public boolean isMessage() {
        Pattern regexp = Pattern.compile("^message[/.*]?$");
        Matcher matcher = regexp.matcher(type);
        return matcher.matches();
    }

    public String type() {
        return type;
    }
}
