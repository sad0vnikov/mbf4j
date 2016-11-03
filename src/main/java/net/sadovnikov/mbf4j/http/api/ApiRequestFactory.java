package net.sadovnikov.mbf4j.http.api;

public abstract class ApiRequestFactory {

    public abstract ApiRequest newRequest(String url);
}
