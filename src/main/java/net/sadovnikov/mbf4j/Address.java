package net.sadovnikov.mbf4j;

public class Address {

    protected String id;
    protected String name;

    public Address(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }
}
