package eu.thingwave.arrowhead.aio.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ServiceRegistryEntry {
    private final ServiceInfo providedService;
    private final ServiceProvider provider;
    private final String serviceURI;
    private final boolean udp;
    private final int version;
    
    public ServiceRegistryEntry(URL url, String systemName, String serviceDefinition, ArrayList<String> interfaces, JsonObject serviceMetadata) {
        version = 1; // hardcoded
        serviceURI = url.getPath();
        switch(url.getProtocol()) {
            case "coap":
                udp = true;
                break;
            default:
                udp = false;
        }
        provider = new ServiceProvider(url, systemName);
        providedService = new ServiceInfo(serviceDefinition, interfaces, serviceMetadata);
    }
    
    public void addInterface(String intface) {
        providedService.addInterface(intface);
    }
    
    public void addInterfaces(ArrayList<String> interfaces) {
        providedService.addInterfaces(interfaces);
    }
    
    public void addMetadata(JsonObject serviceMetadata) {
        providedService.addMetadata(serviceMetadata);
    }
    
    public void addMetadata(String property, String Value) {
        providedService.addMetadata(property, Value);
    }
    
    public void addMetadata(String property, Number Value) {
        providedService.addMetadata(property, Value);
    }
    
    public void addMetadata(String property, boolean Value) {
        providedService.addMetadata(property, Value);
    }
    
    public void addMetadata(String property, Character Value) {
        providedService.addMetadata(property, Value);
    }
    
    public void addMetadata(String property, JsonElement Value) {
        providedService.addMetadata(property, Value);
    }
}
