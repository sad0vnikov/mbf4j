package net.sadovnikov.mbf4j.http.api.emulator;


import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.Request;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthApiRequestDecorator;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthManager;
import net.sadovnikov.mbf4j.http.api.request.DeleteApiRequest;
import net.sadovnikov.mbf4j.http.api.request.GetApiRequest;
import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;
import net.sadovnikov.mbf4j.http.api.request.PutApiRequest;

import java.util.Optional;

public class EmulatorApiRequestFactory extends ApiRequestFactory {



    private String emulatorHost = "localhost";
    private int emulatorPort = 9000;


    public EmulatorApiRequestFactory() {

    }

    public EmulatorApiRequestFactory(String emulatorHost, int emulatorPort) {
        this.emulatorHost = emulatorHost;
        this.emulatorPort = emulatorPort;
    }

    @Override
    public Request get(Channel channel, String url) {
        ApiRequest request = new GetApiRequest("/v3" + url);
        return prepareRequest(request);
    }

    @Override
    public Request post(Channel channel, String url, String body) {
        ApiRequest request = new PostApiRequest("/v3" + url, body);
        return prepareRequest(request);
    }


    @Override
    public Request put(Channel channel, String url) {
        ApiRequest request = new PutApiRequest("/v3" + url);
        return prepareRequest(request);
    }

    @Override
    public Request delete(Channel channel, String url) {
        ApiRequest request = new DeleteApiRequest("/v3" + url);
        return prepareRequest(request);
    }

    protected Request prepareRequest(ApiRequest request) {
        request.setHost(emulatorHost);
        request.setApiPort(emulatorPort);

        if (oAuthManager.isPresent()) {
            return new OAuthApiRequestDecorator(oAuthManager.get(), request);
        }

        return request;
    }
}
