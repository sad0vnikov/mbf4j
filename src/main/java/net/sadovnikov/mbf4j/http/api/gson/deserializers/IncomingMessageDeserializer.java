package net.sadovnikov.mbf4j.http.api.gson.deserializers;


import com.google.gson.*;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;
import net.sadovnikov.mbf4j.http.Conversation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class IncomingMessageDeserializer implements JsonDeserializer<IncomingMessage> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public IncomingMessage deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();

        IncomingMessage message = new IncomingMessage(
                object.get("type").getAsString(),
                object.get("id").getAsString(),
                context.deserialize(object.get("from"), Address.class),
                context.deserialize(object.get("recipient"), Address.class),
                context.deserialize(object.get("conversation"), Conversation.class)
        );

        String channelId = object.get("channelId").getAsString();
        try {
            message.withChannel(new Channel(
                    Channel.Types.valueOf(channelId.toUpperCase())
            ));
        } catch (IllegalArgumentException e) {
            logger.error("got unknown chat id: " + channelId);
        }


        if (object.has("timestamp")) {
            message.withTimestamp(object.get("timestamp").getAsString());
        }

        if (object.has("serviceUrl")) {
            message.withServiceUrl(object.get("serviceUrl").getAsString());
        }

        if (object.has("summary")) {
            message.withSummary(object.get("summary").getAsString());
        }

        if (object.has("text")) {
            message.withText(object.get("text").getAsString());
        }

        return message;
    }
}