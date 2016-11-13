package net.sadovnikov.mbf4j.http.api;

public abstract class ApiRequestFactory {

    public abstract Request get(String url);

    public abstract Request post(String url, String body);

    public abstract Request put(String url);

    public abstract Request delete(String url);
}
