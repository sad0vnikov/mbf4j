package net.sadovnikov.mbf4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;
import net.sadovnikov.mbf4j.events.ActivityEvent;
import net.sadovnikov.mbf4j.events.EventBroker;
import net.sadovnikov.mbf4j.events.EventTypes;
import net.sadovnikov.mbf4j.http.api.gson.deserializers.IncomingMessageDeserializer;
import net.sadovnikov.mbf4j.http.server.HttpEndpoint;
import net.sadovnikov.mbf4j.http.server.HttpHandler;
import net.sadovnikov.mbf4j.http.server.HttpRequest;
import net.sadovnikov.mbf4j.http.server.HttpResponse;

@HttpEndpoint("/api/messages")
public class ApiCallbackHandler extends HttpHandler {

    protected GsonBuilder gsonBuilder;
    protected EventBroker botEventBroker;

    public ApiCallbackHandler(EventBroker eventBroker) {
        this.botEventBroker = eventBroker;
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(IncomingMessage.class, new IncomingMessageDeserializer());
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String requestBody = request.getBody();
        Gson gson = gsonBuilder.create();

        String responseStatus = "OK";
        int httpStatus = HttpResponse.STATUS_OK;
        Activity activity = gson.fromJson(requestBody, Activity.class);

        if (activity.isMessage()) {
            try {
                IncomingMessage message = gson.fromJson(requestBody, IncomingMessage.class);
                botEventBroker.publishEvent(new ActivityEvent<>(EventTypes.EVENT_TYPE_INCOMING_MESSAGE, message));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        JsonObject jsonResponse =  new JsonObject();
        jsonResponse.addProperty("status", responseStatus);
        return new HttpResponse(jsonResponse.toString(), httpStatus);
    }
}
