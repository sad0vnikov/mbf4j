package net.sadovnikov.mbf4j.events;

import net.sadovnikov.mbf4j.IncomingActivityListener;
import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class EventBroker {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Map<EventTypes, Set<IncomingActivityListener>> listeners = new HashMap<>();



    public EventBroker() {
        for (EventTypes type : EventTypes.values()) {
            listeners.put(type, new HashSet<>());
        }
    }

    public <T extends IncomingActivity> void addListener(EventTypes type, IncomingActivityListener<T> listener) {
        Set<IncomingActivityListener> typeListeners = listeners.get(type);
        typeListeners.add(listener);
        logger.info("registered event listener " + listener.getClass().getName() + " for event type " + type.name());
    }

    public void publishEvent(ActivityEvent event) {
        EventTypes eventType = event.type();
        logger.debug("pushing new event " + event.type());
        listeners.get(eventType)
                .stream()
                .forEach(listener -> listener.notify(event.activity()));
    }


}
