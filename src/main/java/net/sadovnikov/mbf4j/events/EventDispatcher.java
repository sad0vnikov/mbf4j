package net.sadovnikov.mbf4j.events;

import net.sadovnikov.mbf4j.IncomingActivityListener;
import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;

public interface EventDispatcher {

    public <T extends IncomingActivity> EventDispatcher on(EventTypes eventType, IncomingActivityListener<T> listener);
}
