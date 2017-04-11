package guru.springframework.listener;

import guru.springframework.SpringBootActiveMQApplication;
import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class MessageListener {

    private ProductRepository productRepository;

    private static final Logger log = Logger.getLogger(MessageListener.class);

    public MessageListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootActiveMQApplication} for more details
     * @param message
     */
    @JmsListener(destination = SpringBootActiveMQApplication.PRODUCT_MESSAGE_QUEUE, containerFactory = "jmsFactory")
    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        Product product = productRepository.findOne(id);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);
        productRepository.save(product);
        log.info("Message processed...");
    }
}
