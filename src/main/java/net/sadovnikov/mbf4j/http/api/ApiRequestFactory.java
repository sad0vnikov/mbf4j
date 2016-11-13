package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.http.api.oauth.OAuthManager;
import java.util.Optional;

public abstract class ApiRequestFactory {

    protected Optional<OAuthManager> oAuthManager = Optional.empty();

    public abstract Request get(String url);

    public abstract Request post(String url, String body);

    public abstract Request put(String url);

    public abstract Request delete(String url);

    public ApiRequestFactory withOauth(OAuthManager oAuthManager) {
        this.oAuthManager = Optional.of(oAuthManager);
        return this;
    }
}
