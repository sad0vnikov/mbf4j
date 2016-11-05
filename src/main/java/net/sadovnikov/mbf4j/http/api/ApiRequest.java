package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.http.HttpRequest;
import net.sadovnikov.mbf4j.http.JsonResponse;

import java.io.IOException;
import java.lang.reflect.Type;

public class ApiRequest {

    private final String host = "apis.skype.com";
    private final int apiPort = 80;

    protected String endpoint;
    protected HttpRequest httpRequest;


    protected String response;

    public ApiRequest(String endpoint) {
        this.endpoint = endpoint;
        this.httpRequest = enformHttpRequest();
    }

    public ApiRequest execute() throws IOException {
        httpRequest.post();
        return this;
    }


    public ApiResponse response() throws ResponseParseException {
        return new JsonResponse(this.httpRequest.response());
    }

    public <T> TypedApiResponse<T> response(Class<T> typeClass) {
        return new TypedApiResponse<T>(this.httpRequest.response());
    }

    private final HttpRequest enformHttpRequest() {
        return new HttpRequest(getApiHost() + ":" + getApiHost() + "/" + endpoint);
    }

    protected String getApiHost() {
        return host;
    }

    protected int getApiPort() {
        return apiPort;
    }
}
