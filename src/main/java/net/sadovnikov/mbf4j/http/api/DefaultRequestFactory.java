package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthApiRequestDecorator;
import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;

public class DefaultRequestFactory extends ApiRequestFactory {

    private final String API_URL = "botframework.com";

    @Override
    public Request get(Channel channel, String endpoint) {
        return null;
    }

    @Override
    public Request post(Channel channel, String endpoint, String body) {
        ApiRequest request = new PostApiRequest("/v3" + endpoint, body);
        request
            .setHost(getApiUrlForChannel(channel))
            .setHttps(true);

        return new OAuthApiRequestDecorator(oAuthManager.get(), request);


    }

    @Override
    public Request put(Channel channel, String endpoint) {
        return null;
    }

    @Override
    public Request delete(Channel channel, String endpoint) {
        return null;
    }

    protected String getApiUrlForChannel(Channel channel) {
        return channel.id() + "." + API_URL;
    }
}
