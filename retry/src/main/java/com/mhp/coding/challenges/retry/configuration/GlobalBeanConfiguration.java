package com.mhp.coding.challenges.retry.configuration;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import com.mhp.coding.challenges.retry.core.inbound.NotificationHandler;
import com.mhp.coding.challenges.retry.core.inbound.RetryNotificationHandler;
import com.mhp.coding.challenges.retry.core.logic.RetryNotificationService;
import com.mhp.coding.challenges.retry.core.outbound.RetryNotificationSaver;
import com.mhp.coding.challenges.retry.inbound.SendEmailRetryListener;
import com.mhp.coding.challenges.retry.core.logic.NotificationService;
import com.mhp.coding.challenges.retry.core.outbound.NotificationSender;
import com.mhp.coding.challenges.retry.persistence.RetryPersistenceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
@PropertySource("classpath:config.properties")
public class GlobalBeanConfiguration {

    @Value("${initialIntervall}")
    private long initialIntervall;

    @Value("${backOffMultiplier}")
    private double backOffMultiplier;

    @Value("${maxAttempts}")
    private Integer maxAttempts;

    @Bean
    public NotificationHandler notificationHandler(NotificationSender notificationSender) {
        return new NotificationService(notificationSender);
    }

    @Bean
    public RetryNotificationHandler retryNotificationHandler(RetryNotificationSaver retryNotificationSaver) {
        return new RetryNotificationService(retryNotificationSaver);
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(initialIntervall);
        exponentialBackOffPolicy.setMultiplier(backOffMultiplier);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxAttempts);
        retryTemplate.setRetryPolicy(retryPolicy);

        retryTemplate.registerListener(
                new SendEmailRetryListener(
                        retryNotificationHandler(new RetryPersistenceAdapter())));

        return retryTemplate;
    }

}
