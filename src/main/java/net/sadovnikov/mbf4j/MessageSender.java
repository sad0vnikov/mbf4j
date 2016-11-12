package net.sadovnikov.mbf4j;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sadovnikov.mbf4j.activities.outcoming.MessageToSend;
import net.sadovnikov.mbf4j.activities.outcoming.SentMessage;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;
import net.sadovnikov.mbf4j.http.api.gson.serializers.MessageToSendSerializer;
import net.sadovnikov.mbf4j.http.api.response.IdApiResponse;

import java.io.IOException;

public class MessageSender {

    protected ApiRequestFactory requestFactory;
    protected GsonBuilder gsonBuilder;

    protected String botName;

    public MessageSender(ApiRequestFactory requestFactory, String botName) {
        this.requestFactory = requestFactory;
        this.botName = botName;

        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MessageToSend.class, new MessageToSendSerializer());
    }

    public SentMessage send(MessageToSend message) throws ApiException,HttpException {
        Gson gson = gsonBuilder.create();
        message.withFrom(new Address(null, botName));

        ApiRequest request = requestFactory.post("/conversations/" + message.conversation().id() + "/activities", gson.toJson(message));
        try {
            request.execute();
            IdApiResponse id = request.response(IdApiResponse.class);
            return new SentMessage(message, id.id());

        } catch (ResponseParseException e) {
            throw new ApiException(e);
        }
    }
}
