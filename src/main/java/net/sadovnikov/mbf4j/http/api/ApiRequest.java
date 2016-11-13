package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;

public abstract class ApiRequest implements Request {

    protected String host = "apis.skype.com";
    protected int apiPort = 80;

    protected String endpoint;
    protected String body;
    protected HttpRequest httpRequest;

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
        HttpRequest httpRequest = new HttpRequest(getProtocol() + getApiHost() + ":" + getApiPort() + "/" + getApiVersion() + endpoint, body);
        httpRequest.addHeader("Content-Type", "application/json");
        httpRequest.addHeader("Charset", "utf-8");

        return httpRequest;
    }

    public String getApiHost() {
        return host;
    }

    public int getApiPort() {
        return apiPort;
    }

    public void setApiPort(int apiPort) {
        this.apiPort = apiPort;
        httpRequest = enformHttpRequest();
    }

    public void setHost(String host) {
        this.host = host;
        httpRequest = enformHttpRequest();
    }

    protected String getProtocol() {
        return "http://";
    }

    protected String getApiVersion() { return "v3"; }
}
