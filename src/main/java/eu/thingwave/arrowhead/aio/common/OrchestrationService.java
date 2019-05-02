package eu.thingwave.arrowhead.aio.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class OrchestrationService {
    private ServiceInfo service;
    private OrchestrationProvider provider;
    private String serviceURI;
    private ArrayList<String> warnings;
    
    
    public URL getServiceURL() throws MalformedURLException {
        return new URL("http", provider.getAddress(), provider.getPort(), serviceURI);
    }
}
