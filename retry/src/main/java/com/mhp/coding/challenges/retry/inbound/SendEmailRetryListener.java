package com.mhp.coding.challenges.retry.inbound;

import com.mhp.coding.challenges.retry.core.entities.EmailNotification;
import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import com.mhp.coding.challenges.retry.core.inbound.RetryNotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Component
public class SendEmailRetryListener extends RetryListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailRetryListener.class);

    private RetryNotificationHandler retryNotificationHandler;

    public SendEmailRetryListener(RetryNotificationHandler retryNotificationHandler) {
        this.retryNotificationHandler = retryNotificationHandler;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        super.close(context, callback, throwable);

        EmailNotification emailNotification = (EmailNotification) context.getAttribute("emailNotification");
        RetryEmailNotification retryEmailNotification = new RetryEmailNotification(emailNotification, context.getRetryCount());

        logger.info("[" + Thread.currentThread().getName()
                + "]: close -> total retries: "
                + context.getRetryCount()
                + " @"
                + LocalTime.now()
                + " to recipient: "
                + emailNotification.getRecipient());


        retryNotificationHandler.retryEmailNotification(retryEmailNotification);
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        super.onError(context, callback, throwable);

        EmailNotification emailNotification = (EmailNotification) context.getAttribute("emailNotification");
        RetryEmailNotification retryEmailNotification = new RetryEmailNotification(emailNotification, context.getRetryCount());

        logger.info("[" + Thread.currentThread().getName()
                + "]: onError -> retryCount: "
                + context.getRetryCount()
                + " @"
                + LocalTime.now()
                + " to recipient: "
                + emailNotification.getRecipient());

        retryNotificationHandler.retryEmailNotification(retryEmailNotification);
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        logger.info("[" + Thread.currentThread().getName() + "]: open @" + LocalTime.now());
        return super.open(context, callback);
    }
}
