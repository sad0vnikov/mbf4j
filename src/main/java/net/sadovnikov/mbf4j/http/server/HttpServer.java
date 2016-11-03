package net.sadovnikov.mbf4j.http.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class HttpServer {

    protected List<HttpHandler> handlers = new CopyOnWriteArrayList<>();

    public void addHandler(HttpHandler httpHandler) {
        handlers.add(httpHandler);
    }

    public abstract void start();

    public abstract void stop();

}


