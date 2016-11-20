package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthApiRequestDecorator;
import net.sadovnikov.mbf4j.http.api.request.DeleteApiRequest;
import net.sadovnikov.mbf4j.http.api.request.GetApiRequest;
import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;
import net.sadovnikov.mbf4j.http.api.request.PutApiRequest;

public class DefaultRequestFactory extends ApiRequestFactory {

    private final String API_URL = "botframework.com";

    @Override
    public Request get(Channel channel, String endpoint) {
        ApiRequest request = new GetApiRequest("/v3" + endpoint);
        return prepareRequest(channel, request);
    }

    @Override
    public Request post(Channel channel, String endpoint, String body) {
        ApiRequest request = new PostApiRequest("/v3" + endpoint, body);
        return prepareRequest(channel, request);
    }

    @Override
    public Request put(Channel channel, String endpoint) {
        ApiRequest request = new PutApiRequest("/v3" + endpoint);
        return prepareRequest(channel, request);
    }

    @Override
    public Request delete(Channel channel, String endpoint) {
        ApiRequest request = new DeleteApiRequest("/v3" + endpoint);
        return prepareRequest(channel, request);
    }

    protected Request prepareRequest(Channel channel, ApiRequest request) {
        request
                .setHost(getApiUrlForChannel(channel))
                .setHttps(true);

        return new OAuthApiRequestDecorator(oAuthManager.get(), request);
    }

    protected String getApiUrlForChannel(Channel channel) {
        return channel.id() + "." + API_URL;
    }
}
