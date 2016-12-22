package net.sadovnikov.mbf4j.activities.outcoming;


public class SentMessage extends MessageToSend {

    private String id;

    public SentMessage(MessageToSend messageToSend, String id) {
        super(messageToSend.channel(), messageToSend.conversation(), messageToSend.text().get());
        this.recipient = messageToSend.recipient();
        this.id = id;
    }

    public String id() {
        return id;
    }
}
