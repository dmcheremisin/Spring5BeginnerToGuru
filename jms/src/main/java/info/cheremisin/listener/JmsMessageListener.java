package info.cheremisin.listener;

import info.cheremisin.model.JmsMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static info.cheremisin.config.JmsConfig.MY_QUEUE;

@Component
public class JmsMessageListener {

    @JmsListener(destination = MY_QUEUE)
    public void listen(@Payload JmsMessage jmsMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        System.out.println(">>> Received a message: " + jmsMessage);
        System.out.println(">>> Message header: " + headers);
    }
}
