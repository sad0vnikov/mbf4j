package net.sadovnikov.mbf4j.events;

import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;

public class ActivityEvent<T extends IncomingActivity> {

    protected EventTypes type;
    protected T activity;

    public ActivityEvent(EventTypes type, T activity) {
        this.activity = activity;
        this.type = type;
    }

    public EventTypes type() {
        return type;
    }

    public T activity() {
        return activity;
    }
}
