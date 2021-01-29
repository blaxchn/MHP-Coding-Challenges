package com.mhp.coding.challenges.retry.core.logic;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import com.mhp.coding.challenges.retry.core.inbound.RetryNotificationHandler;
import com.mhp.coding.challenges.retry.core.outbound.RetryNotificationSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:config.properties")
public class RetryNotificationService implements RetryNotificationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RetryNotificationService.class);

    private RetryNotificationSaver retryNotificationSaver;

    @Value("${maxAttempts}")
    private Integer maxAttempts;

    public RetryNotificationService(RetryNotificationSaver retryNotificationSaver){
        this.retryNotificationSaver = retryNotificationSaver;
    }

    @Override
    public void retryEmailNotification(RetryEmailNotification retryEmailNotification) {
        if (retryEmailNotification.getRetryCount() == maxAttempts) {
            logger.info("-------- maxAttempts reached");
            retryNotificationSaver.dropRetry(retryEmailNotification);
        } else {
            logger.info("-------- continue");
            retryNotificationSaver.saveRetry(retryEmailNotification);
        }
    }

    @Override
    public void retryAllRemainingEmailNotifications() {
        logger.info("-------- starting to retry all remaining EmailNotifications");

        List<RetryEmailNotification> retryEmailNotificationArrayList = retryNotificationSaver.findAllRetries();
        for (RetryEmailNotification retryEmailNotification : retryEmailNotificationArrayList) {
            // TODO: try to send an email to all recipients in the list, with the amount of remaining retries
        }
    }
}
