package net.sadovnikov.mbf4j.activities.incoming;

import net.sadovnikov.mbf4j.Address;
import net.sadovnikov.mbf4j.http.Conversation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationUpdate extends IncomingActivity {

    List<Address> membersAdded;
    List<Address> membersRemoved;
    protected Conversation conversation;

    public ConversationUpdate(String type, String id, Address from, Address recipient, Conversation conversation) {
        super(type, id, from, recipient);
        this.conversation = conversation;
    }

    public List<Address> membersAdded() {
        return membersAdded;
    }

    public ConversationUpdate setMembersAdded(List<Address> membersAdded) {
        this.membersAdded = membersAdded;
        return this;
    }

    public ConversationUpdate setMembersAdded(Address[] membersAdded) {
        return setMembersAdded(Arrays.asList(membersAdded));
    }


    public List<Address> membersRemoved() {
        return membersRemoved;
    }

    public ConversationUpdate setMembersRemoved(List<Address> membersRemoved) {
        this.membersRemoved = membersRemoved;
        return this;
    }

    public ConversationUpdate setMembersRemoved(Address[] membersRemoved) {
        return setMembersRemoved(Arrays.asList(membersRemoved));
    }

    public Conversation conversation() {
        return conversation;
    }


}
