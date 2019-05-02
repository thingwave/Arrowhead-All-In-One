package eu.thingwave.arrowhead.aio.common;

import java.net.URL;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ServiceProvider {
    private final String systemName;
    private final String address;
    private final int port;
    
    public ServiceProvider(URL url, String systemName) {
        address = url.getHost();
        port = url.getPort();
        this.systemName = systemName;
    }
}
