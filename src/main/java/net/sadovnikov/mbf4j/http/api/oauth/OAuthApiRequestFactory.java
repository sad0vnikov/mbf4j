package net.sadovnikov.mbf4j.http.api.oauth;

import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;

public class OAuthApiRequestFactory extends ApiRequestFactory {

    protected SkypeOAuthManager oAuthManager;

    public OAuthApiRequestFactory(SkypeOAuthManager oAuthManager) {
        this.oAuthManager = oAuthManager;
    }

    @Override
    public ApiRequest newRequest(String url) {
        return new OAuthApiRequest(url, oAuthManager);
    }
}
