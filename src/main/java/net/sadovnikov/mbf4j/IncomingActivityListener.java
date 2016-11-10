package net.sadovnikov.mbf4j;


import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;


public interface IncomingActivityListener<T extends IncomingActivity> {

    void notify(T activity);
}
