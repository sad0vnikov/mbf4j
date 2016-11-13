package net.sadovnikov.mbf4j.http.api.request;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PostRequestParams {

    protected Map<String,String> params = new HashMap<>();

    public PostRequestParams() {

    }

    public PostRequestParams(Map<String,String> params) {
        this.params.putAll(params);
    }

    public PostRequestParams addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public String toString() throws IllegalStateException {
        StringBuilder encodedParams = new StringBuilder();

        try {
            for (String keyParam : params.keySet()) {
                if (encodedParams.length() > 0) {
                    encodedParams.append("&");
                }
                encodedParams.append(URLEncoder.encode(keyParam, "UTF-8"));
                encodedParams.append("=");
                encodedParams.append(URLEncoder.encode(params.get(keyParam), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }


        return encodedParams.toString();
    }


}
