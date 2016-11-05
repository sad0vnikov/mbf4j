package net.sadovnikov.mbf4j.http.api;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class TypedApiResponse<T> extends ApiResponse {

    public TypedApiResponse(String response) {
        super(response);
    }

    public T getObject(Class<T> typeParameterClass) throws ResponseParseException {
        try {
            Gson gson  = new Gson();
            T result = gson.fromJson(response, typeParameterClass);

            return result;

        } catch (JsonSyntaxException e) {
            throw new ResponseParseException(e);
        }
    }

}
