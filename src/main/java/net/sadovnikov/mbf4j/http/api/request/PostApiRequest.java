package net.sadovnikov.mbf4j.http.api.request;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.HttpRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiResponseParser;
import net.sadovnikov.mbf4j.http.api.response.ApiResponse;
import net.sadovnikov.mbf4j.http.api.response.ErrorApiResponse;
import net.sadovnikov.mbf4j.http.server.HttpResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PostApiRequest extends ApiRequest {

    public PostApiRequest(String endpoint, String body) {
        super(endpoint, body);
    }

    @Override
    protected Set<Integer> getOkStatuses() {
        Set<Integer> statuses = new HashSet<Integer>();
        statuses.add(httpRequest.STATUS_OK);
        statuses.add(httpRequest.STATUS_ACCEPTED);
        statuses.add(httpRequest.STATUS_CREATED);
        return statuses;
    }

    @Override
    protected void executeRequest() throws HttpException {
        httpRequest.post();
    }
}
