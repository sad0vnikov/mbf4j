package net.sadovnikov.mbf4j.http.server;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface HttpEndpoint {
    String value();
}
