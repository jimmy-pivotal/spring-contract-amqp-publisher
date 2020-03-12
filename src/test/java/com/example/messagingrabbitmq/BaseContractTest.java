package com.example.messagingrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagingRabbitmqApplication.class)
@AutoConfigureMessageVerifier
public class BaseContractTest {

    @SpyBean
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void onUserIsOnline() throws Exception {
        Publisher publisher = new Publisher(rabbitTemplate);
        publisher.send();
    }
}
