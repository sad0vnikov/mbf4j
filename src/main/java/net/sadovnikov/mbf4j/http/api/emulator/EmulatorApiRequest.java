package net.sadovnikov.mbf4j.http.api.emulator;


import net.sadovnikov.mbf4j.http.api.ApiRequest;

/**
 * Makes request to BotFramework emulator
 */
public class EmulatorApiRequest extends ApiRequest {

    private String host = "localhost";
    private int apiPort = 9000;

    public EmulatorApiRequest(String endpoint) {
        super(endpoint);
    }

    public EmulatorApiRequest(String endpoint, String host, int apiPort) {
        this(endpoint);
        this.host = host;
        this.apiPort = apiPort;
    }

    @Override
    protected String getApiHost() {
        return this.host;
    }

    @Override
    protected int getApiPort() {
        return this.apiPort;
    }
}
