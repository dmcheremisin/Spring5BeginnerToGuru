package info.cheremisin.sender;

import info.cheremisin.model.JmsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static info.cheremisin.config.JmsConfig.MY_QUEUE;

@Component
@RequiredArgsConstructor
public class JmsSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("!!! I'm sending message");

        JmsMessage message = JmsMessage.builder()
                .id(UUID.randomUUID())
                .message("Test message")
                .build();

        jmsTemplate.convertAndSend(MY_QUEUE, message);

        System.out.println(">>> Message was sent!");

    }
}
