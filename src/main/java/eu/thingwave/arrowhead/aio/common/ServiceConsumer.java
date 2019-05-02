/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.thingwave.arrowhead.aio.common;

import java.net.URL;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ServiceConsumer {
    private final String systemName;
    private final String address;
    private final int port;
    private final String authenticationInfo;
    
    public ServiceConsumer(URL url, String systemName, String authenticationInfo) {
        address = url.getHost();
        port = url.getPort();
        this.systemName = systemName;
        this.authenticationInfo = authenticationInfo;
    }
}
