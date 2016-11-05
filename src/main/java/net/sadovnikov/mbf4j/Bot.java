package net.sadovnikov.mbf4j;

import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.oauth.SkypeOAuthManager;
import net.sadovnikov.mbf4j.http.server.impl.DefaultHttpServer;
import net.sadovnikov.mbf4j.http.server.HttpServer;

import java.io.IOException;

public class Bot {

    protected static final int DEFAULT_HTTP_PORT = 3978;

    protected ApiRequestFactory apiRequestFactory;

    protected HttpServer httpServer;
    protected CallbackActivityListener callbackActivityListener = new CallbackActivityListener();

    protected String clientId;
    protected String clientSecret;
    protected String callbackUri;


    public Bot setHttpServer(HttpServer server) {
        this.httpServer = server;

        return this;
    }

    public Bot onActivityReceived(IncomingActivityHandler<?> handler) {
        callbackActivityListener.addActivityHandler(handler);
        return this;
    }

    public Bot setApiRequestFactory(ApiRequestFactory factory) {
        this.apiRequestFactory = factory;
        return this;
    }

    public void init() throws IOException {
        if (httpServer == null) {
            httpServer = new DefaultHttpServer(DEFAULT_HTTP_PORT);
        }

        httpServer.addHandler(callbackActivityListener);

        if (apiRequestFactory == null) {
            SkypeOAuthManager oAuthManager = new SkypeOAuthManager(clientId, clientSecret);
            apiRequestFactory = new OAuthApiRequestFactory(oAuthManager);
        }

        httpServer.start();
    }
}
