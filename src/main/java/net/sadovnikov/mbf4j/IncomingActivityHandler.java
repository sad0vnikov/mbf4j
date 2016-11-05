package net.sadovnikov.mbf4j;


import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;

import java.lang.reflect.ParameterizedType;

public interface IncomingActivityHandler<T extends IncomingActivity> {

    void handle(T activity);

    default boolean checkCanHandle(Class type) {
        ParameterizedType genericTypes = (ParameterizedType) this.getClass().getGenericSuperclass();
        return genericTypes.getActualTypeArguments()[0].getClass().isAssignableFrom(type);
    }
}
