package net.sadovnikov.mbf4j.http.api.emulator;


import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;
import net.sadovnikov.mbf4j.http.api.Request;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthApiRequestDecorator;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthManager;
import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;

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
    public ApiRequest get(String url) {
        return null;
    }

    @Override
    public Request post(String url, String body) {
        ApiRequest request = new PostApiRequest("/v3" + url, body);
        request.setHost(emulatorHost);
        request.setApiPort(emulatorPort);

        if (oAuthManager.isPresent()) {
            return new OAuthApiRequestDecorator(oAuthManager.get(), request);
        }

        return request;
    }

    @Override
    public ApiRequest put(String url) {
        return null;
    }

    @Override
    public ApiRequest delete(String url) {
        return null;
    }
}
