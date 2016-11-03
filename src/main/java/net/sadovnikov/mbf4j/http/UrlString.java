package net.sadovnikov.mbf4j.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

class UrlString {

    protected String url;

    public UrlString(String url) {
        this.url = url;
    }

    public UrlString(String url, Map<String,String> params) {
        this(url);
        appendUrlParams(params);
    }

    public UrlString appendUrlParams(Map<String,String> params) {
        if (params.size() > 0) {
            if (!url.contains("?")) {
                this.url += "?";
            }

            this.url += mapToUrlRequestString(params);
        }

        return this;
    }

    public String toString() {
        return url;
    }

    public String mapToUrlRequestString(Map<String,String> params)  {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String key : params.keySet()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }

                stringBuilder
                        .append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(key, "UTF-8"));
            }


            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }

    }
}
