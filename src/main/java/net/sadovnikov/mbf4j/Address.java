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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        return name != null ? name.equals(address.name) : address.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
