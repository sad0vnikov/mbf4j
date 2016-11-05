package net.sadovnikov.mbf4j.activities.incoming;


import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.activities.Activity;

public class IncomingActivity extends Activity {

    protected String type;
    protected String id;
    protected Address from;
    protected Address recepient;

    public IncomingActivity(String type, String id, Address from, Address recepient) {
        this.type = type;
        this.id = id;
        this.from = from;
        this.recepient = recepient;
    }

}
