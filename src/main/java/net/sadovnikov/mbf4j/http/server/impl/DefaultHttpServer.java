package net.sadovnikov.mbf4j.http.server.impl;

import net.sadovnikov.mbf4j.http.server.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DefaultHttpServer extends HttpServer {

    protected com.sun.net.httpserver.HttpServer httpServer;

    private static final int DEFAULT_HTTP_PORT = 8080;
    private int port;

    public DefaultHttpServer() throws IOException {
        this(DEFAULT_HTTP_PORT);
    }

    public DefaultHttpServer(int port) throws IOException {
        this.port = port;
        httpServer = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(port), 0);
    }

    public void start() {
        httpServer.createContext("/", new DefaultHttpListener(handlers));
        httpServer.start();

    }

    public void stop() {
        httpServer.stop(0);
    }

    @Override
    public int port() {
        return port;
    }
}
