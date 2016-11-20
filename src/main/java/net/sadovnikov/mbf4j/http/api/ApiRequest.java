package net.sadovnikov.mbf4j.http.api;

import com.google.gson.Gson;
import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;
import net.sadovnikov.mbf4j.http.api.response.ErrorApiResponse;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    protected Set<Integer> getOkStatuses() {
        Set<Integer> statuses = new HashSet<>();
        statuses.add(httpRequest.STATUS_OK);
        return statuses;
    }

    protected abstract void executeRequest() throws HttpException;

    public ApiResponseParser execute() throws ApiException, HttpException {
        executeRequest();
        int responseCode = httpRequest.responseCode();
        Set<Integer> okStatuses = getOkStatuses();
        if (!okStatuses.contains(responseCode)) {
            throw new ApiException(httpRequest.responseBody());
        }
        return new ApiResponseParser(httpRequest.responseBody());
    }



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
