package net.sadovnikov.mbf4j.http.server;


import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String body;
    private Map<String,String> params;

    public HttpRequest(Map<String,String> params, String body) {
        this.body = body;
        this.params = params;
    }

    public static HttpRequest fromHttpExchange(HttpExchange httpExchange) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
        String requestBody = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            requestBody += line;
        }

        return new HttpRequest(new HashMap<String,String>(), requestBody);
    }

    public String getBody() {
        return this.body;
    }
}

