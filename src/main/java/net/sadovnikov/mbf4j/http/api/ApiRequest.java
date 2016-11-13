package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;

import java.util.Optional;

public abstract class ApiRequest implements Request {

    protected String host = "apis.skype.com";
    protected Optional<Integer> apiPort = Optional.empty();

    protected String endpoint;
    protected String body;
    protected HttpRequest httpRequest;

    protected boolean isHTTPS = false;

    protected String response;

    public ApiRequest(String endpoint, String body) {
        this.endpoint = endpoint;
        this.body = body;
        this.httpRequest = enformHttpRequest();
    }

    public ApiRequest(String endpoint) {
        this(endpoint, "");
    }

    public HttpRequest httpRequest() {
        return httpRequest;
    }

    public abstract ApiResponseParser execute() throws ApiException, HttpException;



    protected final HttpRequest enformHttpRequest() {
        HttpRequest httpRequest = new HttpRequest(getProtocol() + getApiHost() + ":" + getApiPort() + endpoint, body);
        httpRequest.addHeader("Content-Type", "application/json");
        httpRequest.addHeader("Charset", "utf-8");

        return httpRequest;
    }

    public String getApiHost() {
        return host;
    }

    public int getApiPort() {

        if (apiPort.isPresent()) {
            return apiPort.get();
        }

        int port = 80;
        if (isHTTPS) {
            port = 443;
        }

        return port;
    }

    public ApiRequest setApiPort(int apiPort) {
        this.apiPort = Optional.of(apiPort);
        httpRequest = enformHttpRequest();
        return this;
    }

    public ApiRequest setHost(String host) {
        this.host = host;
        httpRequest = enformHttpRequest();
        return this;
    }


    public ApiRequest setHttps(boolean isHTTPS) {
        this.isHTTPS = isHTTPS;
        httpRequest = enformHttpRequest();
        return this;
    }

    protected String getProtocol() {
        return isHTTPS ? "https://" : "http://";
    }
}
