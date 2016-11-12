package net.sadovnikov.mbf4j.http.api.oauth;


import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestDecorator;
import net.sadovnikov.mbf4j.http.api.response.ApiResponse;

import java.io.IOException;

public class OAuthApiRequestDecorator extends ApiRequestDecorator {

    protected SkypeOAuthManager oAuthManager;

    public OAuthApiRequestDecorator(SkypeOAuthManager oAuthManager) {
        this.oAuthManager = oAuthManager;
    }

    @Override
    public ApiRequest execute(ApiRequest request) throws ApiException,HttpException {

        try {
            SkypeOAuthManager.SkypeOAuthSession oAuthSession = oAuthManager.getSession();
            request.httpRequest().addHeader("Authorization", "Bearer " + oAuthSession.getAccessToken());

        } catch (HttpException e) {
            throw new ApiException(e);
        }

        request.execute();

        return request;
    }
}
