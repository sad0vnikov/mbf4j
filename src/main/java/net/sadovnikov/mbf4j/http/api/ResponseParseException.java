package net.sadovnikov.mbf4j.http.api;

public class ResponseParseException extends Exception {

    public ResponseParseException() {
        super();
    }

    protected ResponseParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseParseException(Throwable cause) {
        super(cause);
    }

    public ResponseParseException(String message) {
        super(message);
    }

    public ResponseParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
