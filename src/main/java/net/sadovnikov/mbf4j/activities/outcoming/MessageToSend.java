package net.sadovnikov.mbf4j.activities.outcoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.UploadedAttachment;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.http.Conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageToSend extends Activity {


    protected String text;
    protected Optional<Address> recipient = recipient().empty();
    protected Address from;
    protected Conversation conversation;
    protected Channel channel;
    protected List<UploadedAttachment> attachments = new ArrayList<>();

    public final String TYPE_TEXT = "message";

    public MessageToSend(Channel channel, Conversation conversation, String text) {
        type = TYPE_TEXT;
        this.text = text;
        this.conversation = conversation;
        this.channel = channel;
    }

    public MessageToSend(Channel channel, Conversation conversation, Address address, String text) {
        this(channel, conversation, text);
        this.recipient = Optional.of(address);
    }


    public Channel channel() { return channel; }

    public Optional<Address> recipient() {
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

    public MessageToSend addAttachment(UploadedAttachment attachment) {
        this.attachments.add(attachment);
        return this;
    }

    public UploadedAttachment[] attachments() {
        UploadedAttachment[] attachmentsArray = new UploadedAttachment[attachments.size()];
        return attachments.toArray(attachmentsArray);
    }
}
