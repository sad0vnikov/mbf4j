package net.sadovnikov.mbf4j.activities.outcoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.http.Conversation;

import java.util.Optional;

public class MessageToSend extends Activity {


    protected String text;
    protected Address recipient;
    protected Address from;
    protected Conversation conversation;
    protected Channel channel;

    public final String TYPE_TEXT = "message";

    public MessageToSend(Channel channel, Conversation conversation, Address address, String text) {
        type = TYPE_TEXT;
        this.text = text;
        this.recipient = address;
        this.conversation = conversation;
        this.channel = channel;
    }

    public Channel channel() { return channel; }

    public Address recipient() {
        return recipient;
    }

    public Optional<String> text() {
        return Optional.ofNullable(text);
    }

    public Conversation conversation() {
        return conversation;
    }

    public Optional<Address> from() {
        return Optional.ofNullable(from);
    }

    public MessageToSend withFrom(Address from) {
        this.from = from;
        return this;
    }
}
