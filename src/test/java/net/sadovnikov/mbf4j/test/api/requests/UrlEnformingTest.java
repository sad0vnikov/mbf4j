package net.sadovnikov.mbf4j.test.api.requests;

import net.sadovnikov.mbf4j.http.api.request.PostApiRequest;
import org.junit.Test;
import org.junit.Assert;

public class UrlEnformingTest {


    @Test
    public void postUrlEnforming() {

        PostApiRequest request = new PostApiRequest("/test/uri", "");
        request.setHost("example.org")
                .setHttps(true);

        Assert.assertEquals("https://example.org:443/test/uri", request.httpRequest().urlString());

        request = new PostApiRequest("/test/uri", "");
        request.setHost("example.org")
                .setHttps(false);

        Assert.assertEquals("http://example.org:80/test/uri", request.httpRequest().urlString());

    }

    @Test
    public void postUrlEnformingWithPort() {

        PostApiRequest request = new PostApiRequest("/test/uri", "");
        request.setHost("example.org")
                .setApiPort(8080);

        Assert.assertEquals("http://example.org:8080/test/uri", request.httpRequest().urlString());

    }
}
