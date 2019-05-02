package eu.thingwave.arrowhead.aio;

import eu.thingwave.arrowhead.aio.consumer.AhConsumer;
import eu.thingwave.arrowhead.aio.producer.AhProducer;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pablo Puñal Pereira <pablo.punal@thingwave.eu>
 */
public class ArrowheadAIO {
    private static final Logger LOG = LoggerFactory.getLogger(ArrowheadAIO.class.getName());
    private final AhProducer producer;
    private final AhConsumer consumer;
    
    public ArrowheadAIO(URL serviceRegistry, URL orchestration) {
        LOG.debug("New ArrowheadAIO");
        producer = new AhProducer(serviceRegistry);
        consumer = new AhConsumer(orchestration);
    }
    
    public AhProducer getProducer() {
        return producer;
    }
    
    public AhConsumer getConsumer() {
        return consumer;
    }
}
