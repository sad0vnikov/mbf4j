package net.sadovnikov.mbf4j.http.api.gson.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.activities.outcoming.MessageToSend;

import java.lang.reflect.Type;


public class MessageToSendSerializer implements JsonSerializer<MessageToSend> {

    @Override
    public JsonElement serialize(MessageToSend messageToSend, Type type, JsonSerializationContext context) {
        JsonObject root = new JsonObject();
        root.addProperty("type", messageToSend.type());
        messageToSend.text().ifPresent(
                text -> root.addProperty("text", text)
        );

        root.add("recepient", context.serialize(messageToSend.recepient(), Address.class));

        messageToSend.from().ifPresent(
                from -> root.add("from", context.serialize(from, Address.class))
        );



        return root;
    }
}
