package net.sadovnikov.mbf4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.activities.incoming.ConversationUpdate;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;
import net.sadovnikov.mbf4j.events.ActivityEvent;
import net.sadovnikov.mbf4j.events.EventBroker;
import net.sadovnikov.mbf4j.events.EventTypes;
import net.sadovnikov.mbf4j.http.api.gson.deserializers.ConversationUpdateDeserializer;
import net.sadovnikov.mbf4j.http.api.gson.deserializers.IncomingMessageDeserializer;
import net.sadovnikov.mbf4j.http.server.HttpEndpoint;
import net.sadovnikov.mbf4j.http.server.HttpHandler;
import net.sadovnikov.mbf4j.http.server.HttpRequest;
import net.sadovnikov.mbf4j.http.server.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@HttpEndpoint("/api/messages")
public class ApiCallbackHandler extends HttpHandler {

    protected GsonBuilder gsonBuilder;
    protected EventBroker botEventBroker;

    Logger logger = LoggerFactory.getLogger(getClass());

    public ApiCallbackHandler(EventBroker eventBroker) {
        this.botEventBroker = eventBroker;
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(IncomingMessage.class, new IncomingMessageDeserializer());
        gsonBuilder.registerTypeAdapter(ConversationUpdate.class, new ConversationUpdateDeserializer());
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String requestBody = request.getBody();
        Gson gson = gsonBuilder.create();

        logger.debug("got http request: " + requestBody);
        logger.info("got incoming activity via http webhook");

        String responseStatus = "OK";
        int httpStatus = HttpResponse.STATUS_OK;
        Activity activity = gson.fromJson(requestBody, Activity.class);

        try {
            if (activity.type().equals(activity.TYPE_MESSAGE)) {
                IncomingMessage message = gson.fromJson(requestBody, IncomingMessage.class);
                logger.info("got incoming message from " + message.from().name + " (id = " + message.from().id()
                        + "), channel_id = " + message.channel().id() + ", text = " + message.text().get());
                botEventBroker.publishEvent(new ActivityEvent<>(EventTypes.EVENT_TYPE_INCOMING_MESSAGE, message));
            }

            if (activity.type().equals(activity.TYPE_CONVERSATION_UPDATE)) {
                ConversationUpdate conversationUpdate = gson.fromJson(requestBody, ConversationUpdate.class);
                logger.info("got new conversation update event (conversation id = " + conversationUpdate.conversation().id() + ")");
                botEventBroker.publishEvent(new ActivityEvent<>(EventTypes.EVENT_TYPE_CONVERSATION_UPDATE, conversationUpdate));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


        JsonObject jsonResponse =  new JsonObject();
        jsonResponse.addProperty("status", responseStatus);
        return new HttpResponse(jsonResponse.toString(), httpStatus);
    }
}
