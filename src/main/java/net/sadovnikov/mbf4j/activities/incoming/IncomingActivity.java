package net.sadovnikov.mbf4j.activities.incoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.activities.Activity;

public class IncomingActivity extends Activity {

    protected String type;
    protected String id;
    protected Address from;
    protected Address recipient;
    protected Channel channel;

    public IncomingActivity(String type, String id, Address from, Address recipient) {
        this.type = type;
        this.id = id;
        this.from = from;
        this.recipient = recipient;
    }

    public String id() {
        return id;
    }

    public Address from() {
        return from;
    }

    public Address recipient() {
        return recipient;
    }

    public IncomingActivity withChannel(Channel channel) {
        this.channel = channel;
        return this;
    }

    public Channel channel() {
        return channel;
    }
}
