package net.sadovnikov.mbf4j.activities.incoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.http.Conversation;

import java.util.Optional;

public class IncomingMessage extends IncomingActivity {

    protected final String TYPE_TEXT = "message/text";

    protected String serviceUrl = null;
    protected String timestamp = null;
    protected String summary = null;
    protected String text = null;
    protected Conversation conversation;

    public IncomingMessage(String type, String id, Address from, Address to, Conversation conversation) {
        super(type, id, from, to);
        this.conversation = conversation;
    }



    public IncomingMessage withServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }

    public Optional<String> timestamp() {
        return Optional.ofNullable(timestamp);
    }

    public IncomingMessage withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Optional<String> summary() {
        return Optional.ofNullable(summary);
    }

    public IncomingMessage withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Optional<String> text() {
        return Optional.ofNullable(text);
    }

    public IncomingMessage withText(String text) {
        this.text = text;
        return this;
    }

    public Conversation conversation() {
        return conversation;
    }

    @Override
    public boolean isMessage() {
        return true;
    }
}
