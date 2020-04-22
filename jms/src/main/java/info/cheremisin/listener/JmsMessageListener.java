package info.cheremisin.listener;

import info.cheremisin.model.JmsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

import java.util.UUID;

import static info.cheremisin.config.JmsConfig.MY_QUEUE;
import static info.cheremisin.config.JmsConfig.SEND_RECEIVE_QUEUE;

@Component
@RequiredArgsConstructor
public class JmsMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = MY_QUEUE)
    public void listen(@Payload JmsMessage jmsMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        System.out.println(">>> Received a message: " + jmsMessage);
        //System.out.println(">>> Message header: " + headers);
    }

    @JmsListener(destination = SEND_RECEIVE_QUEUE)
    public void listenForHello(@Payload JmsMessage jmsMessage,
                       @Headers MessageHeaders headers,
                       Message message) throws JMSException {
        System.out.println(">>> Listener received message: " + jmsMessage);

        JmsMessage world = JmsMessage.builder()
                .id(UUID.randomUUID())
                .message("World")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), world);
    }
}
