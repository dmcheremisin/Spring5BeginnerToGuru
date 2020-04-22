package info.cheremisin.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.cheremisin.model.JmsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.UUID;

import static info.cheremisin.config.JmsConfig.MY_QUEUE;
import static info.cheremisin.config.JmsConfig.SEND_RECEIVE_QUEUE;

@Component
@RequiredArgsConstructor
public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        System.out.println("!!! I'm sending message");

        JmsMessage message = JmsMessage.builder()
                .id(UUID.randomUUID())
                .message("Test message")
                .build();

        jmsTemplate.convertAndSend(MY_QUEUE, message);

        System.out.println(">>> Message was sent!");

    }

    @Scheduled(fixedRate = 5000)
    public void sendAndReceiveMessage() throws JMSException {
        System.out.println("!!! Send And Receive Message Prepared");

        JmsMessage message = JmsMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(SEND_RECEIVE_QUEUE, session -> {
            try {
                System.out.println(">>> Sending JmsMessage Hello");

                TextMessage textMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                textMessage.setStringProperty("_type", "info.cheremisin.model.JmsMessage");

                return textMessage;
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Boom!");
            }
        });

        System.out.println(">>> Sender Received message: ");
        System.out.println(receivedMessage.getBody(String.class));
        //System.out.println(objectMapper.readValue((receivedMessage.getBody(String.class)), JmsMessage.class));
    }
}
