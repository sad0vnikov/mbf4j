package net.sadovnikov.mbf4j;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.sadovnikov.mbf4j.activities.Activity;
import net.sadovnikov.mbf4j.activities.incoming.IncomingActivity;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;
import net.sadovnikov.mbf4j.http.server.HttpEndpoint;
import net.sadovnikov.mbf4j.http.server.HttpHandler;
import net.sadovnikov.mbf4j.http.server.HttpRequest;
import net.sadovnikov.mbf4j.http.server.HttpResponse;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@HttpEndpoint("/api/messages")
public class CallbackActivityListener extends HttpHandler {

    protected List<IncomingActivityHandler> activityHandlers = new CopyOnWriteArrayList<>();

    public void addActivityHandler(IncomingActivityHandler handler) {
        activityHandlers.add(handler);
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String requestBody = request.getBody();
        Gson gson = new Gson();

        String responseStatus = "OK";
        int httpStatus = HttpResponse.STATUS_OK;
        Activity activity = gson.fromJson(requestBody, Activity.class);

        if (activity.isMessage()) {
            IncomingMessage message = gson.fromJson(requestBody, IncomingMessage.class);
            activityHandlers.stream()
                    .filter((IncomingActivityHandler handler) -> handler.checkCanHandle(message.getClass()))
                    .forEach((IncomingActivityHandler handler) -> handler.handle(message));
        }

        JsonObject jsonResponse =  new JsonObject();
        jsonResponse.addProperty("status", responseStatus);
        return new HttpResponse(jsonResponse.toString(), httpStatus);
    }
}
