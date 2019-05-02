/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thingwave.arrowhead.aio.common;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ConfigurationOrchestrationFlags {
    private boolean onlyPreferred = false;
    private boolean overrideStore = true;
    private boolean externalServiceRequest = false;
    private boolean enableInterCloud = true;
    private boolean enableQoS = false;
    private boolean matchmaking = false;
    private boolean metadataSearch = true;
    private boolean triggerInterCloud = false;
    private boolean pingProviders = false;
    
    public ConfigurationOrchestrationFlags() {
        
    }
}
