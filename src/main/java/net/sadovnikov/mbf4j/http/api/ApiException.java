package net.sadovnikov.mbf4j.http.api;


import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.response.ErrorApiResponse;

public class ApiException extends HttpException {

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(ErrorApiResponse response) {
        super(response.message());
    }

    protected ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
