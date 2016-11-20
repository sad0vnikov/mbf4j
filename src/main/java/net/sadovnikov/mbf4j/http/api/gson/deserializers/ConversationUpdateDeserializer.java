package net.sadovnikov.mbf4j.http.api.gson.deserializers;

import com.google.gson.*;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.activities.incoming.ConversationUpdate;
import net.sadovnikov.mbf4j.http.Conversation;

import java.lang.reflect.Type;

public class ConversationUpdateDeserializer implements JsonDeserializer<ConversationUpdate> {

    @Override
    public ConversationUpdate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        ConversationUpdate event = new ConversationUpdate(
                object.get("type").getAsString(),
                object.get("id").getAsString(),
                context.deserialize(object.get("from"), Address.class),
                context.deserialize(object.get("recipient"), Address.class),
                context.deserialize(object.get("conversation"), Conversation.class)
        );

        Address[] membersAdded = context.deserialize(object.get("membersAdded"), Address[].class);
        if (membersAdded != null) {
            event.setMembersAdded(membersAdded);
        }


        Address[] membersRemoved = context.deserialize(object.get("membersRemoved"), Address[].class);
        if (membersRemoved != null) {
            event.setMembersRemoved(membersRemoved);
        }

        return event;
    }
}
