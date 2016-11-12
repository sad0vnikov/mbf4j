package net.sadovnikov.mbf4j.http.api;

public abstract class ApiRequestFactory {

    public abstract ApiRequest get(String url);

    public abstract ApiRequest post(String url, String body);

    public abstract ApiRequest put(String url);

    public abstract ApiRequest delete(String url);
}
