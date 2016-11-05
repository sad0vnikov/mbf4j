package net.sadovnikov.mbf4j.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.sadovnikov.mbf4j.http.api.ApiResponse;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse extends ApiResponse {

    public JsonResponse(String response) throws ResponseParseException {
        super(response);
    }

    public Map<String, Object> asMap() throws ResponseParseException {
        try {
            Gson gson = new Gson();
            Map object =  gson.fromJson(this.response, new TypeToken<Map<String,Object>>(){}.getType());

            return object;

        } catch (JsonSyntaxException e) {
            throw new ResponseParseException("trying to turn array JSON into Map");
        }

    }
}
