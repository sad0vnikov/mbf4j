package net.sadovnikov.mbf4j.http.api.emulator;


import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.ApiRequestFactory;

public class EmulatorApiRequestFactory extends ApiRequestFactory {



    private String emulatorHost = "localhost";
    private int emulatorPort = 9000;

    public EmulatorApiRequestFactory() {

    }

    public EmulatorApiRequestFactory(String emulatorHost, int emulatorPort) {
        this.emulatorHost = emulatorHost;
        this.emulatorPort = emulatorPort;
    }

    @Override
    public ApiRequest newRequest(String url) {
        return new EmulatorApiRequest(url, emulatorHost, emulatorPort);
    }
}
