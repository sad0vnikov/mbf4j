package net.sadovnikov.mbf4j.http.api.oauth;


import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiResponseParser;
import net.sadovnikov.mbf4j.http.api.Request;

public class OAuthApiRequestDecorator implements Request {

    protected SkypeOAuthManager oAuthManager;
    protected ApiRequest request;

    public OAuthApiRequestDecorator(SkypeOAuthManager oAuthManager, ApiRequest request) {
        this.oAuthManager = oAuthManager;
        this.request = request;
    }

    public ApiResponseParser execute() throws ApiException,HttpException {

        try {
            SkypeOAuthManager.SkypeOAuthSession oAuthSession = oAuthManager.getSession();
            request.httpRequest().addHeader("Authorization", "Bearer " + oAuthSession.getAccessToken());

        } catch (HttpException e) {
            throw new ApiException(e);
        }

        return request.execute();
    }
}
