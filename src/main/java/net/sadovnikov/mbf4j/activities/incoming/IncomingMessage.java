package net.sadovnikov.mbf4j.activities.incoming;


import net.sadovnikov.mbf4j.Address;

import java.util.Optional;

public class IncomingMessage extends IncomingActivity {

    protected final String TYPE_TEXT = "message/text";

    protected String serviceUrl = null;
    protected String timestamp = null;
    protected String summary = null;
    protected String channelId = null;
    protected String text = null;

    public IncomingMessage(String type, String id, Address from, Address to, String channelId) {
        super(type, id, from, to);
        this.channelId = channelId;
    }


    public Optional<String> serviceUrl() {
        return Optional.ofNullable(channelId);
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


    @Override
    public boolean isMessage() {
        return true;
    }
}
