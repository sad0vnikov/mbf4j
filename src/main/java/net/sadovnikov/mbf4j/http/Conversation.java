package net.sadovnikov.mbf4j.http;

public class Conversation {

    protected String id;
    protected String name;
    protected boolean isGroup;

    public Conversation(String id, String name, boolean isGroup) {
        this.id = id;
        this.name = name;
        this.isGroup = isGroup;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean isGroup() {
        return isGroup;
    }
}
