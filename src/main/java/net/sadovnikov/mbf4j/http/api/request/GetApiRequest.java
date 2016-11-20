package net.sadovnikov.mbf4j.http.api.request;

import com.google.gson.Gson;
import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiResponseParser;
import net.sadovnikov.mbf4j.http.api.response.ErrorApiResponse;

import java.util.HashSet;
import java.util.Set;

public class GetApiRequest extends ApiRequest {

    public GetApiRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    protected Set<Integer> getOkStatuses() {
        Set<Integer> statuses = new HashSet<>();
        statuses.add(httpRequest.STATUS_OK);
        return statuses;
    }

    @Override
    protected void executeRequest() throws HttpException {
        httpRequest.get();
    }
}
