package eu.thingwave.arrowhead.aio.common;

import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ServiceRequestForm {
    private final ServiceConsumer requesterSystem;
    private final ServiceInfo requestedService;
    private final ConfigurationOrchestrationFlags orchestrationFlags;
    private final ArrayList<String> preferredProviders;
    private final JsonObject requestedQoS;
    private final JsonObject commands;
    
    public ServiceRequestForm(
            ServiceConsumer requesterSystem,
            ServiceInfo requestedServiceInfo,
            ConfigurationOrchestrationFlags orchestrationFlags,
            ArrayList<String> preferredProviders,
            JsonObject requestedQoS,
            JsonObject commands) {
        this.requesterSystem = requesterSystem;
        this.requestedService = requestedServiceInfo;
        this.orchestrationFlags = orchestrationFlags;
        this.preferredProviders = preferredProviders;
        this.requestedQoS = requestedQoS;
        this.commands = commands;
    }
}
