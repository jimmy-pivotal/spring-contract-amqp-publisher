package com.example.messagingrabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

@SpringBootTest
@AutoConfigureMessageVerifier
@SpyBean
public abstract class BaseContractTest {

    @Autowired
    private MessagingRabbitmqApplication application;

    protected void onUserIsOnline() throws Exception {
        application.send();
    }
}
