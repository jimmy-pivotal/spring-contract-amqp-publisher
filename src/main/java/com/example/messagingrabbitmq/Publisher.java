package com.example.messagingrabbitmq;

import com.example.messagingrabbitmq.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private final RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo",
                new Person("John", "Smith"));
    }
}
