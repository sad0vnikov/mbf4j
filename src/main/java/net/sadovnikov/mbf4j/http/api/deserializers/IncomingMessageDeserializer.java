package net.sadovnikov.mbf4j.http.api.deserializers;


import com.google.gson.*;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;

import java.lang.reflect.Type;

public class IncomingMessageDeserializer implements JsonDeserializer<IncomingMessage> {

    @Override
    public IncomingMessage deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();
        IncomingMessage message = new IncomingMessage(
                object.get("type").getAsString(),
                object.get("id").getAsString(),
                context.deserialize(object.get("from"), Address.class),
                context.deserialize(object.get("to"), Address.class),
                object.get("channelId").getAsString()
        );

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