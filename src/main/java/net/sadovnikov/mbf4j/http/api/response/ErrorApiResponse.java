package net.sadovnikov.mbf4j.http.api.response;


public class ErrorApiResponse extends ApiResponse {

    protected String error;
    protected String code;
    protected String message;

    public String error() {
        return error;
    }

    public String message() {
        return message;
    }

    public String code() {
        return code;
    }
}
