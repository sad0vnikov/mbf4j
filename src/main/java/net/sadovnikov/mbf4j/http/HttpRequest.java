package net.sadovnikov.mbf4j.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    protected String host;
    protected String body;
    protected Map<String,String> headers;

    protected UrlString urlString;

    protected String response;
    protected int responseCode;
    protected String responseMessage;

    public enum methods {GET, POST, PUT, DELETE};

    public final int STATUS_OK = 200;
    public final int STATUS_CREATED = 201;
    public final int STATUS_ACCEPTED = 202;
    public final int STATUS_BAD_REQUEST = 400;
    public final int STATUS_UNAUTHORIZED = 401;
    public final int STATUS_FORBIDDEN = 403;
    public final int STATUS_NOT_FOUND = 404;
    public final int STATUS_INVALID_METHOD = 405;
    public final int STATUS_ERROR = 500;
    public final int STATUS_UNAVAILABLE = 503;


    protected Logger logger = LoggerFactory.getLogger(getClass());

    public HttpRequest(String url, String body, Map<String,String> headers) {

        this.urlString = new UrlString(url);
        this.headers = headers;
        this.body = body;
    }

    public HttpRequest(String url, String body) {
        this(url, body, new HashMap<String, String>());
    }

    public HttpRequest(String url) {
        this(url, "");
    }

    public HttpRequest post() throws HttpException {
        execute(methods.POST);
        return this;
    }

    public HttpRequest get() throws HttpException {
        execute(methods.GET);
        return this;
    }

    public HttpRequest put() throws HttpException {
        execute(methods.PUT);
        return this;
    }

    public HttpRequest delete() throws HttpException {
        execute(methods.DELETE);
        return this;
    }

    public HttpRequest addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public String responseBody() {
        return this.response;
    }

    public int responseCode() {
        return responseCode;
    }

    public String responseMessage() {
        return responseMessage;
    }

    protected void execute(methods method) throws HttpException {
        try {

            logger.debug("executing " + method.name() + " HTTP request to " + urlString.toString());
            logger.debug("request body = " + body);

            URL urlObj = new URL(this.urlString.toString());
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod(method.name());
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }


            if (body.length() > 0) {
                connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

                out.write(body);
                out.flush();
                out.close();
            }

            this.responseCode = connection.getResponseCode();
            this.responseMessage = connection.getResponseMessage();
            InputStream in;
            if (responseCode <= 200 || responseCode <= 299) {
                in = connection.getInputStream();
            } else {
                in = connection.getErrorStream();
            }

            BufferedReader reader =  new BufferedReader(new InputStreamReader(in));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            in.close();


            connection.disconnect();

            this.response = response.toString();
            logger.debug("got HTTP response: " + this.response);
        } catch (Exception e) {
            throw new HttpException(e);
        }

    }

    public String urlString() {
        return urlString.toString();
    }
}
