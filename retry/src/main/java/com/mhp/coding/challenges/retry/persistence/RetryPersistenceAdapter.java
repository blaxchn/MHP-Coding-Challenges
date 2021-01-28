package com.mhp.coding.challenges.retry.persistence;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import com.mhp.coding.challenges.retry.core.outbound.RetryNotificationSaver;
import org.springframework.stereotype.Service;

@Service
public class RetryPersistenceAdapter implements RetryNotificationSaver {

    @Override
    public void saveRetry(RetryEmailNotification retryEmailNotification) {

        // TODO: access MongoDB @ RetryRepository#save

    }

    @Override
    public void dropRetry(RetryEmailNotification retryEmailNotification) {

        // TODO: access MongoDB @ RetryRepository#delete

    }
}
