package net.sadovnikov.mbf4j.http.server.impl;


import net.sadovnikov.mbf4j.http.server.HttpEndpoint;
import net.sadovnikov.mbf4j.http.server.HttpHandler;

import com.sun.net.httpserver.*;
import net.sadovnikov.mbf4j.http.server.HttpRequest;
import net.sadovnikov.mbf4j.http.server.HttpResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class DefaultHttpListener implements com.sun.net.httpserver.HttpHandler {

    List<HttpHandler> handlers;

    public DefaultHttpListener(List<HttpHandler> handlers) {
        this.handlers = handlers;
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        String responseBody = "<html><body>404 Not Found</body></html>";
        int statusCode = HttpResponse.STATUS_NOT_FOUND;

        String requestUri = httpExchange.getRequestURI().toString();
        for (HttpHandler handler : handlers) {

            HttpEndpoint endpointAnnotation = handler.getClass().getAnnotation(HttpEndpoint.class);
            if (endpointAnnotation.value().equals(requestUri)) {

                HttpRequest httpRequest = HttpRequest.fromHttpExchange(httpExchange);
                HttpResponse response = handler.handle(httpRequest);

                responseBody = response.body();
                statusCode   = response.status();

                break;
            }

        }


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
        httpExchange.sendResponseHeaders(statusCode, responseBody.length());
        out.write(responseBody);
        out.flush();
        httpExchange.close();


    }
}
