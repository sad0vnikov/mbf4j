package net.sadovnikov.mbf4j.http.api.request;

import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;

import java.util.Set;

public class PutApiRequest extends ApiRequest {

    public PutApiRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    protected void executeRequest() throws HttpException {
        httpRequest.put();
    }
}
