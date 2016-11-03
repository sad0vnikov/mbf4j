package net.sadovnikov.mbf4j.http.server;


public class HttpResponse {

    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_FORBIDDEN   = 403;
    public static final int STATUS_NOT_FOUND   = 404;
    public static final int STATUS_INTERNAL_ERROR = 500;

    private String body;
    private int status = STATUS_OK;

    public HttpResponse(String responseBody, int responseStatus) {
        this.body = responseBody;
        this.status = responseStatus;
    }

    public HttpResponse(String responseBody) {
        this.body = responseBody;
    }

    public String body() {
        return body;
    }

    public int status() {
        return status;
    }
}
