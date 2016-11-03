package net.sadovnikov.mbf4j.http;


import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    protected String host;
    protected Map<String,String> bodyParams;
    protected Map<String,String> headers;

    protected UrlString urlString;

    protected String response;
    protected int responseCode;
    protected String responseMessage;

    public enum methods {GET, POST, PUT, DELETE};

    public HttpRequest(String url, Map<String,String> bodyParams, Map<String,String> headers) {

        this.urlString = new UrlString(url);
        this.bodyParams = bodyParams;
        this.headers = headers;
    }

    public HttpRequest(String url, Map<String,String> bodyParams) {
        this(url, new HashMap<String,String>(), new HashMap<String, String>());
    }

    public HttpRequest(String url) {
        this(url, new HashMap<String,String>());
    }

    public HttpRequest post() throws IOException {
        execute(methods.POST);
        return this;
    }

    public HttpRequest get() throws IOException {
        execute(methods.GET);
        return this;
    }

    public HttpRequest put() throws IOException {
        execute(methods.PUT);
        return this;
    }

    public HttpRequest delete() throws IOException {
        execute(methods.DELETE);
        return this;
    }

    public HttpRequest addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HttpRequest addBodyParam(String key, String value) {
        bodyParams.put(key, value);
        return this;
    }

    public String response() {
        return this.response;
    }

    public int responseCode() {
        return responseCode;
    }

    public String responseMessage() {
        return responseMessage;
    }

    protected void execute(methods method) throws IOException {
        URL urlObj = new URL(this.urlString.toString());
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod(method.name());
        connection.setUseCaches(false);
        connection.setDoOutput(false);
        for (String key : headers.keySet()) {
            connection.setRequestProperty(key, headers.get(key));
        }


        if (this.bodyParams.size() > 0) {
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String encodedBodyParams = urlString.mapToUrlRequestString(bodyParams);
            out.writeBytes(encodedBodyParams);
            out.flush();
            out.close();
            connection.setRequestProperty("Content-Length", String.valueOf(encodedBodyParams.length()));
        }

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        in.close();

        this.responseCode = connection.getResponseCode();
        this.responseMessage = connection.getResponseMessage();
        connection.disconnect();

        this.response = response.toString();
    }

}
