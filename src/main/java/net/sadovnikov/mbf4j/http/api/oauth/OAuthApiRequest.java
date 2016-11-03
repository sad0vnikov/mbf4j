package net.sadovnikov.mbf4j.http.api.oauth;


import net.sadovnikov.mbf4j.http.api.ApiRequest;

import java.io.IOException;

public class OAuthApiRequest extends ApiRequest {

    protected SkypeOAuthManager oAuthManager;

    public OAuthApiRequest(String endpoint, SkypeOAuthManager oAuthManager) {
        super(endpoint);
        this.oAuthManager = oAuthManager;

        try {
            SkypeOAuthManager.SkypeOAuthSession oAuthSession = oAuthManager.getSession();
            httpRequest.addHeader("Authorization", "Bearer " + oAuthSession.getAccessToken());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
