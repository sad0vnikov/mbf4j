package net.sadovnikov.mbf4j.http.server;



public abstract class HttpHandler {

    public abstract HttpResponse handle(HttpRequest request);
}
