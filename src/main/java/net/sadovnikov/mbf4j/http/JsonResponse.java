package net.sadovnikov.mbf4j.http;

import net.sadovnikov.mbf4j.http.api.ApiResponse;
import net.sadovnikov.mbf4j.http.api.ResponseParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class JsonResponse extends ApiResponse {

    public JsonResponse(String response) throws ResponseParseException {
        super(response);
    }

    @Override
    public Map<String, Object> asMap() throws ResponseParseException {
        try {
            JSONParser parser = new JSONParser();
            Map object = (Map) parser.parse(this.response);

            return object;

        } catch (ClassCastException | ParseException e) {
            throw new ResponseParseException("trying to turn array JSON into Map");
        }

    }
}
