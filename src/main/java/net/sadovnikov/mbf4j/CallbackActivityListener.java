package net.sadovnikov.mbf4j;

import net.sadovnikov.mbf4j.http.server.HttpEndpoint;
import net.sadovnikov.mbf4j.http.server.HttpHandler;
import net.sadovnikov.mbf4j.http.server.HttpRequest;
import net.sadovnikov.mbf4j.http.server.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        JSONParser jsonParser = new JSONParser();

        String responseStatus = "OK";
        int httpStatus = HttpResponse.STATUS_OK;
        try {
            JSONObject jsonRequest = (JSONObject) jsonParser.parse(requestBody);
            String activityType = (String) jsonRequest.get("type");
            String activityId = (String) jsonRequest.get("id");

            Activity activity = new Activity(activityType, activityId);

            activityHandlers.stream()
                    .forEach((IncomingActivityHandler handler) -> handler.handle(activity));

        } catch (ParseException e) {
            responseStatus = "error";
            httpStatus = HttpResponse.STATUS_BAD_REQUEST;
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", responseStatus);
        return new HttpResponse(jsonResponse.toJSONString(), httpStatus);
    }
}
