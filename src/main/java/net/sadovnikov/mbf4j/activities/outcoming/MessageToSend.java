package net.sadovnikov.mbf4j.activities.outcoming;


import net.sadovnikov.mbf4j.activities.Activity;

public class MessageToSend extends Activity {


    protected String text;
    protected String conversationId;

    public final String TYPE_TEXT = "message/text";

    public MessageToSend(String conversationId, String text) {
        type = TYPE_TEXT;
        this.text = text;
        this.conversationId = conversationId;
    }

    public String conversationId() {
        return conversationId;
    }

    public String text() {
        return text;
    }
}
