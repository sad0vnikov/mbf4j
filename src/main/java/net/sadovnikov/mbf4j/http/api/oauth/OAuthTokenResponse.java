package net.sadovnikov.mbf4j.http.api.oauth;

public class OAuthTokenResponse {

    protected String token_type;
    protected int expires_in;
    protected int ext_expires_in;
    protected String access_token;


    public String tokenType() {
        return token_type;
    }

    public int expiresIn() {
        return expires_in;
    }

    public int extExpiresInn() {
        return ext_expires_in;
    }

    public String accessToken() {
        return access_token;
    }
}
