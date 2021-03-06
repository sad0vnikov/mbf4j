package net.sadovnikov.mbf4j.http.api.gson.deserializers;

import com.google.gson.*;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.activities.incoming.ConversationUpdate;
import net.sadovnikov.mbf4j.http.Conversation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class ConversationUpdateDeserializer implements JsonDeserializer<ConversationUpdate> {

    protected Logger logger = LoggerFactory.getLogger(getClass());


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

        String channelId = object.get("channelId").getAsString();
        try {
            event.withChannel(new Channel(
                    Channel.Types.valueOf(channelId.toUpperCase())
            ));
        } catch (IllegalArgumentException e) {
            logger.error("got unknown channel id: " + channelId);
        }

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
