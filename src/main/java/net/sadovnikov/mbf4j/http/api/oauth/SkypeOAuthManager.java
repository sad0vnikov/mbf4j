package net.sadovnikov.mbf4j.http.api.oauth;


import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;

import java.io.IOException;

public class SkypeOAuthManager {

    protected String clientId;
    protected String clientSecret;

    protected SkypeOAuthSession currentSession;

    public SkypeOAuthManager(String clientId, String clientSecret)
    {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    public SkypeOAuthSession getSession() {
        if (currentSession == null) {
            currentSession = new SkypeOAuthSession();
        }

        return currentSession;
    }

    protected class SkypeOAuthSession {

        protected String accessToken;
        protected long tokenExpiresIn;
        protected long tokenGrantTime;

        public String getAccessToken() throws HttpException {
            long now = System.currentTimeMillis() / 1000L;
            if (accessToken == null || tokenGrantTime + tokenExpiresIn >= now) {
                accessToken = requestAccessToken();
            }

            return accessToken;
        }

        protected String requestAccessToken() throws HttpException {
            String requestUrl = "https://login.microsoftonline.com/common/oauth2/v2.0/token?" +
                    "client_id=" + clientId +
                    "&client_secret=" + clientSecret +
                    "grant_type=client_credentials&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default";

            HttpRequest request = new HttpRequest(requestUrl);
            request.get();

            return request.responseBody();
        }
    }
}
