package net.sadovnikov.mbf4j.http.api.gson.serializers;

import com.google.gson.*;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.UploadedAttachment;
import net.sadovnikov.mbf4j.activities.outcoming.MessageToSend;
import net.sadovnikov.mbf4j.http.Conversation;

import java.lang.reflect.Type;


public class MessageToSendSerializer implements JsonSerializer<MessageToSend> {

    @Override
    public JsonElement serialize(MessageToSend messageToSend, Type type, JsonSerializationContext context) {
        JsonObject root = new JsonObject();
        root.addProperty("type", messageToSend.type());
        messageToSend.text().ifPresent(
                text -> root.addProperty("text", text)
        );

        if (messageToSend.recipient().isPresent()) {
            root.add("recipient", context.serialize(messageToSend.recipient().get(), Address.class));
        }
        root.add("conversation", context.serialize(messageToSend.conversation(), Conversation.class));

        messageToSend.from().ifPresent(
                from -> root.add("from", context.serialize(from, Address.class))
        );

        if (messageToSend.attachments().length > 0) {
            JsonArray attachments = new JsonArray();
            for (UploadedAttachment attachment : messageToSend.attachments()) {
                attachments.add(context.serialize(attachment, UploadedAttachment.class));
            }
            root.add("attachments", attachments);
        }



        return root;
    }
}
