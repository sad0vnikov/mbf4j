package net.sadovnikov.mbf4j;

import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;
import net.sadovnikov.mbf4j.events.EventBroker;
import net.sadovnikov.mbf4j.events.EventDispatcher;
import net.sadovnikov.mbf4j.events.EventTypes;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.DefaultRequestFactory;
import net.sadovnikov.mbf4j.http.api.emulator.EmulatorApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthManager;
import net.sadovnikov.mbf4j.http.api.response.AttachmentViewApiResponse;
import net.sadovnikov.mbf4j.http.server.impl.DefaultHttpServer;
import net.sadovnikov.mbf4j.http.server.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class Bot implements EventDispatcher {

    protected static final int DEFAULT_HTTP_PORT = 3978;

    protected ApiRequestFactory apiRequestFactory = new DefaultRequestFactory();

    protected HttpServer httpServer;
    protected EventBroker eventBroker = new EventBroker();
    protected ApiCallbackHandler apiCallbackHandler = new ApiCallbackHandler(eventBroker);

    protected Optional<String> clientId = Optional.empty();
    protected Optional<String> clientSecret = Optional.empty();
    protected String botName = "default-bot";
    Logger logger = LoggerFactory.getLogger(getClass());


    public Bot setHttpServer(HttpServer server) {
        this.httpServer = server;

        return this;
    }

    public <T extends IncomingActivity> Bot on(EventTypes type, IncomingActivityListener<T> listener) {
        eventBroker.addListener(type, listener);
        return this;
    }

    public Bot setApiRequestFactory(ApiRequestFactory factory) {
        this.apiRequestFactory = factory;
        if (clientSecret.isPresent() && clientId.isPresent()) {
            factory.withOauth(new OAuthManager(clientId.get(), clientSecret.get()));
        }
        logger.info("using " + factory.getClass().getName() + " api request factory");
        return this;
    }

    public Bot setCredentials(String clientId, String clientSecret) {
        this.clientId = Optional.of(clientId);
        this.clientSecret = Optional.of(clientSecret);
        apiRequestFactory.withOauth(new OAuthManager(clientId, clientSecret));
        return this;
    }

    public MessageSender messageSender() {
        return new MessageSender(apiRequestFactory, botName);
    }

    public AttachmentRepository attachments() {
        return new AttachmentRepository(apiRequestFactory);
    }


    public void init() throws IOException {
        if (httpServer == null) {
            httpServer = new DefaultHttpServer(DEFAULT_HTTP_PORT);
        }

        httpServer.addHandler(apiCallbackHandler);

        if (apiRequestFactory == null) {
            apiRequestFactory = new EmulatorApiRequestFactory();
        }

        httpServer.start();
        logger.info("listening to HTTP callbacks at :" + httpServer.port());
    }
}
