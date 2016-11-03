package net.sadovnikov.mbf4j.http.api;


import java.util.Map;

public abstract class ApiResponse {

    protected String response;

    public ApiResponse(String response) {
        this.response = response;
    }

    public String raw() {
        return this.response;
    }

    public abstract Map<String,Object> asMap() throws ResponseParseException;


}
