package net.sadovnikov.mbf4j.activities.outcoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.http.Conversation;

import java.util.Optional;

public class MessageToSend extends Activity {


    protected String text;
    protected Address recepient;
    protected Address from;
    protected Conversation conversation;
    protected String channelId;

    public final String TYPE_TEXT = "message";

    public MessageToSend(Conversation conversation, Address address, String text) {
        type = TYPE_TEXT;
        this.text = text;
        this.recepient = address;
        this.conversation = conversation;
    }

    public void withChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Address recepient() {
        return recepient;
    }

    public Optional<String> text() {
        return Optional.ofNullable(text);
    }

    public Conversation conversation() {
        return conversation;
    }

    public String channelId() {
        return channelId;
    }

    public Optional<Address> from() {
        return Optional.ofNullable(from);
    }

    public MessageToSend withFrom(Address from) {
        this.from = from;
        return this;
    }
}
