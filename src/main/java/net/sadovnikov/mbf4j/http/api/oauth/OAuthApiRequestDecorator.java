package net.sadovnikov.mbf4j.http.api.oauth;


import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiResponseParser;
import net.sadovnikov.mbf4j.http.api.Request;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;

public class OAuthApiRequestDecorator implements Request {

    protected OAuthManager oAuthManager;
    protected ApiRequest request;

    public OAuthApiRequestDecorator(OAuthManager oAuthManager, ApiRequest request) {
        this.oAuthManager = oAuthManager;
        this.request = request;
    }

    public ApiResponseParser execute() throws ApiException,HttpException {

        try {
            OAuthManager.SkypeOAuthSession oAuthSession = oAuthManager.getSession();
            request.httpRequest().addHeader("Authorization", "Bearer " + oAuthSession.getAccessToken());

        } catch (ResponseParseException e) {
            throw new ApiException(e);
        }

        return request.execute();
    }

    @Override
    public String getEnformedUrl() {
        return request.getEnformedUrl();
    }
}
