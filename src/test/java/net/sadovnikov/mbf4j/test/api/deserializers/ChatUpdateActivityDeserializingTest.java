package net.sadovnikov.mbf4j.test.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.activities.incoming.ConversationUpdate;
import net.sadovnikov.mbf4j.activities.incoming.IncomingMessage;
import net.sadovnikov.mbf4j.http.Conversation;
import net.sadovnikov.mbf4j.http.api.gson.deserializers.ConversationUpdateDeserializer;
import net.sadovnikov.mbf4j.http.api.gson.deserializers.IncomingMessageDeserializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChatUpdateActivityDeserializingTest {

    private GsonBuilder gsonBuilder;

    public ChatUpdateActivityDeserializingTest() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConversationUpdate.class, new ConversationUpdateDeserializer());
    }

    @Test
    public void testDeseaializingJsonEventData() {
        String json = "{\n" +
                "  \"type\": \"conversationUpdate\",\n" +
                "  \"membersAdded\": [\n" +
                "    {\n" +
                "      \"id\": \"default-bot\",\n" +
                "      \"name\": \"Bot\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"default-user\",\n" +
                "      \"name\": \"User\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": \"4hbbhmn4l17gjcg0i\",\n" +
                "  \"channelId\": \"emulator\",\n" +
                "  \"timestamp\": \"2016-12-22T20:08:23.844Z\",\n" +
                "  \"recipient\": {\n" +
                "    \"id\": \"default-bot\"\n" +
                "  },\n" +
                "  \"conversation\": {\n" +
                "    \"id\": \"l3j6gk39k566jmng3\"\n" +
                "  },\n" +
                "  \"serviceUrl\": \"https://8ea6524f.ngrok.io\",\n" +
                "  \"from\": {\n" +
                "    \"id\": \"default-user\",\n" +
                "    \"name\": \"User\"\n" +
                "  }\n" +
                "}";

        Gson gson = gsonBuilder.create();
        ConversationUpdate conversationUpdate = gson.fromJson(json, ConversationUpdate.class);

        List<Address> membersAdded = new ArrayList<>();
        membersAdded.add(new Address("default-bot", "Bot"));
        membersAdded.add(new Address("default-user", "User"));

        List<Address> membersRemoved = new ArrayList<>();
        assertEquals(membersRemoved, conversationUpdate.membersRemoved());

        assertEquals(
                membersAdded,
                conversationUpdate.membersAdded()
        );

        assertEquals(
                "4hbbhmn4l17gjcg0i",
                conversationUpdate.id()
        );

        assertEquals(
                new Channel(Channel.Types.EMULATOR),
                conversationUpdate.channel()
        );

        assertEquals(
                "default-bot",
                conversationUpdate.recipient().id()
        );

        assertEquals(
                "l3j6gk39k566jmng3",
                conversationUpdate.conversation().id()
        );

        assertEquals(
                false,
                conversationUpdate.conversation().isGroup()
        );
    }
}
