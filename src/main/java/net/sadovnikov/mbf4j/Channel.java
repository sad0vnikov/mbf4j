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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        return type == channel.type;

    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
