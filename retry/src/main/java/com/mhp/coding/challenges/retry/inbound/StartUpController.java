package com.mhp.coding.challenges.retry.inbound;

import com.mhp.coding.challenges.retry.core.inbound.RetryNotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartUpController {

    private static final Logger logger = LoggerFactory.getLogger(StartUpController.class);

    private RetryNotificationHandler retryNotificationHandler;

    public StartUpController(RetryNotificationHandler retryNotificationHandler) {
        this.retryNotificationHandler = retryNotificationHandler;
    }

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        logger.info("--- Application started! ---");
        retryNotificationHandler.retryAllRemainingEmailNotifications();
    }
}
