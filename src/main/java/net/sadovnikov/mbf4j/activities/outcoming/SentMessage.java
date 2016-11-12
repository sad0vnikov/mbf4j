package net.sadovnikov.mbf4j.activities.outcoming;


public class SentMessage extends MessageToSend {

    private String id;

    public SentMessage(MessageToSend messageToSend, String id) {
        super(messageToSend.conversation(), messageToSend.recepient(), messageToSend.text().get());
        this.id = id;
    }

    public String id() {
        return id;
    }
}
