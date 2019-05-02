package eu.thingwave.arrowhead.aio.producer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import eu.thingwave.arrowhead.aio.common.ServiceRegistryEntry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.HttpEntityEnclosingRequest;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class AhProducer {
    private static final Logger LOG = LoggerFactory.getLogger(AhProducer.class.getName());
    private final CloseableHttpClient httpClient;
    private final Gson gson = new Gson();
    private final URL serviceRegistry;
    
    public AhProducer(URL serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        httpClient = new DefaultHttpClient(httpParams);
    }
    
    private CloseableHttpResponse httpRequest(HttpUriRequest request) throws IOException {
        request.setHeader(CONTENT_TYPE, "application/json");
        HttpEntityEnclosingRequest req = (HttpEntityEnclosingRequest) request;
        LOG.debug("["+request.getMethod()+" REQUEST] ");
        CloseableHttpResponse response = httpClient.execute(request);
        LOG.debug("["+request.getMethod()+" RESPONSE "+response.getStatusLine().getStatusCode()+"]");
        return response;
    }
    
    private int registerServiceAH(ServiceRegistryEntry entry) {
        try {
            HttpPost post = new HttpPost(serviceRegistry.toString()+"/register");
            LOG.debug("ServiceRegistryEntry: "+gson.toJson(entry));
            post.setEntity(new StringEntity(gson.toJson(entry)));
            int status = httpRequest(post).getStatusLine().getStatusCode();
            post.releaseConnection();
            LOG.debug("Registry Status: "+status);
            return status;
        } catch (IOException ex) {}
        return 600;
    }
    
    private int unregisterServiceAH(ServiceRegistryEntry entry) {
        try {
            HttpPut put = new HttpPut(serviceRegistry.toString()+"/remove");
            LOG.debug("ServiceRegistryEntry: "+gson.toJson(entry));
            put.setEntity(new StringEntity(gson.toJson(entry)));
            int status = httpRequest(put).getStatusLine().getStatusCode();
            put.releaseConnection();
            LOG.debug("Unregistry Status: "+status);
            return status;
        } catch (IOException ex) {}
        return 600;
    }
    
    public boolean registerService(ServiceRegistryEntry entry) {
        int status = registerServiceAH(entry);
        if (status == 201) // registration OK
            return true;
        if (status == 400) { // Already registered? -> unregister -> register
            unregisterServiceAH(entry);
            status = registerServiceAH(entry);
            if (status == 201) // registration OK
                return true;
        }
        // not contiue -> bad request
        return false;
    }
    
    public boolean unregisterService(ServiceRegistryEntry entry) {
        int status = unregisterServiceAH(entry);
        return status == 200;
    }
    
}
