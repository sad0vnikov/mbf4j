package net.sadovnikov.mbf4j;

public class Channel {

    public enum Types {
        EMULATOR,
        SKYPE,
        TELEGRAM,
        WEBCHAT
    }

    protected Types type;

    public Channel(Types type) {
        this.type = type;
    }

    public Types type() {
        return type;
    }

    public String id() {
        return type.toString().toLowerCase();
    }
}
