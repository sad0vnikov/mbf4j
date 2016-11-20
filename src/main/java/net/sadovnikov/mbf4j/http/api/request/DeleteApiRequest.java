package net.sadovnikov.mbf4j.http.api.request;

import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;

import java.util.Set;

public class DeleteApiRequest extends ApiRequest {

    public DeleteApiRequest(String endpoint) {
        super(endpoint);
    }

    @Override
    protected Set<Integer> getOkStatuses() {
        Set<Integer> statuses = super.getOkStatuses();
        statuses.add(httpRequest.STATUS_NO_CONTENT);
        return statuses;
    }

    @Override
    protected void executeRequest() throws HttpException {
        httpRequest.delete();
    }
}
