package com.mhp.coding.challenges.retry.core.logic;

import com.mhp.coding.challenges.retry.core.entities.EmailNotification;
import com.mhp.coding.challenges.retry.core.inbound.NotificationHandler;
import com.mhp.coding.challenges.retry.core.outbound.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationService implements NotificationHandler {

    private NotificationSender notificationSender;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private RetryTemplate retryTemplate;

    public NotificationService(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    @Override
    @Async
    public EmailNotification processEmailNotification(EmailNotification emailNotification) {

        retryTemplate.execute( retryContext -> {
            retryContext.setAttribute("emailNotification", emailNotification);
            notificationSender.sendEmail(emailNotification);
            return null;
        });

        return emailNotification;
    }
}
