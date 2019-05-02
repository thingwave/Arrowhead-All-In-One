package eu.thingwave.arrowhead.aio.consumer;

import com.google.gson.Gson;
import eu.thingwave.arrowhead.aio.common.OrchestrationResponse;
import eu.thingwave.arrowhead.aio.common.ServiceRequestForm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.HttpEntityEnclosingRequest;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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
public class AhConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(AhConsumer.class.getName());
    private final CloseableHttpClient httpClient;
    private final Gson gson = new Gson();
    private final URL orchestration;
    
    public AhConsumer(URL orchestration) {
        this.orchestration = orchestration;
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
    
    private CloseableHttpResponse orchestrationAH(ServiceRequestForm serviceRequestForm) {
        try {
            HttpPost post = new HttpPost(orchestration.toString()+"/orchestration");
            LOG.debug("ServiceRequestForm: "+gson.toJson(serviceRequestForm));
            post.setEntity(new StringEntity(gson.toJson(serviceRequestForm)));
            CloseableHttpResponse response = httpRequest(post);
            post.releaseConnection();
            return response;
        } catch (IOException ex) {}
        return null;
    }
    
    
    public OrchestrationResponse orchestration(ServiceRequestForm serviceRequestForm) {
  
        CloseableHttpResponse response = orchestrationAH(serviceRequestForm);
        
        if (response.getEntity() == null) return new OrchestrationResponse();
        
        try {
            return gson.fromJson(
                    new BufferedReader(
                            new InputStreamReader(
                                    response.getEntity().getContent())),
                    OrchestrationResponse.class);
        } catch (IOException ex) {
            LOG.warn("IOException: "+ex.getLocalizedMessage());
            return new OrchestrationResponse();
        } catch (UnsupportedOperationException ex) {
            LOG.warn("UnsupportedOperationException: "+ex.getLocalizedMessage());
            return new OrchestrationResponse();
        }
    }
    
}
