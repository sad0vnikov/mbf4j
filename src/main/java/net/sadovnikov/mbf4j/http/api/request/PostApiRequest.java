package net.sadovnikov.mbf4j.http.api.request;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.response.ApiResponse;
import net.sadovnikov.mbf4j.http.api.response.ErrorApiResponse;

import java.io.IOException;

public class PostApiRequest extends ApiRequest {

    public PostApiRequest(String endpoint, String body) {
        super(endpoint, body);
    }

    @Override
    public PostApiRequest execute() throws ApiException, HttpException {
        httpRequest().post();
        int responseCode = httpRequest.responseCode();
        if (responseCode != httpRequest.STATUS_OK && responseCode != httpRequest.STATUS_CREATED && responseCode != httpRequest.STATUS_ACCEPTED) {
            Gson gson = new Gson();
            ErrorApiResponse response = gson.fromJson(httpRequest.responseBody(), ErrorApiResponse.class);
            throw new ApiException(httpRequest.responseBody());
        }
        return this;

    }
}
