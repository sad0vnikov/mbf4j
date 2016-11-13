package net.sadovnikov.mbf4j.http.api;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.sadovnikov.mbf4j.http.api.response.ApiResponse;

public class ApiResponseParser {

    String response;

    public ApiResponseParser(String response) {
        this.response = response;
    }

    public <T> T getObject(Class<T> typeParameterClass) throws ResponseParseException {
        try {
            Gson gson  = new Gson();
            T result = gson.fromJson(response, typeParameterClass);

            return result;

        } catch (JsonSyntaxException e) {
            throw new ResponseParseException(e);
        }
    }

}
