package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.Channel;
import net.sadovnikov.mbf4j.http.api.oauth.OAuthManager;
import java.util.Optional;

public abstract class ApiRequestFactory {

    protected Optional<OAuthManager> oAuthManager = Optional.empty();

    public abstract Request get(Channel channel, String url);

    public abstract Request post(Channel channel, String url, String body);

    public abstract Request put(Channel channel, String url);

    public abstract Request delete(Channel channel, String url);

    public ApiRequestFactory withOauth(OAuthManager oAuthManager) {
        this.oAuthManager = Optional.of(oAuthManager);
        return this;
    }
}
