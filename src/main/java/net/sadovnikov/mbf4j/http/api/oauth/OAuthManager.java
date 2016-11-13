package net.sadovnikov.mbf4j.http.api.oauth;


import com.google.gson.Gson;
import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;
import net.sadovnikov.mbf4j.http.api.ApiResponseParser;
import net.sadovnikov.mbf4j.http.api.Request;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;
import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;
import net.sadovnikov.mbf4j.http.api.request.PostRequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OAuthManager {

    protected String clientId;
    protected String clientSecret;

    protected SkypeOAuthSession currentSession;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public OAuthManager(String clientId, String clientSecret)
    {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    public SkypeOAuthSession getSession() {
        if (currentSession == null) {
            logger.info("instatiating new OAuth session");
            currentSession = new SkypeOAuthSession();
        }

        return currentSession;
    }

    protected class SkypeOAuthSession {

        protected String token;
        protected long tokenExpiresIn;
        protected long tokenGrantTime;

        public String getAccessToken() throws HttpException, ApiException, ResponseParseException {
            long now = System.currentTimeMillis() / 1000L;
            if (token == null || tokenGrantTime + tokenExpiresIn >= now) {
                OAuthTokenResponse accessTokenResponse = requestAccessToken();
                tokenGrantTime = System.currentTimeMillis();
                tokenExpiresIn = accessTokenResponse.expiresIn() * 1000;
                token = accessTokenResponse.accessToken();
            }

            return token;
        }

        protected OAuthTokenResponse requestAccessToken() throws HttpException, ApiException, ResponseParseException {
            logger.info("requiesting new OAuth token");
            Gson gson = new Gson();

            PostRequestParams requestParams = new PostRequestParams();
            requestParams
                    .addParam("scope", "https://graph.microsoft.com/.default")
                    .addParam("grant_type", "client_credentials")
                    .addParam("client_id", clientId)
                    .addParam("client_secret", clientSecret);

            HttpRequest request = new HttpRequest("https://login.microsoftonline.com/common/oauth2/v2.0/token",
                    requestParams.toString());

            String responseBody = request.post().responseBody();

            return new ApiResponseParser(responseBody).getObject(OAuthTokenResponse.class);
        }
    }
}
