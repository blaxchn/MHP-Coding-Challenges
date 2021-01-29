package com.mhp.coding.challenges.retry.persistence;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import com.mhp.coding.challenges.retry.core.outbound.RetryNotificationSaver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetryPersistenceAdapter implements RetryNotificationSaver {

    @Override
    public void saveRetry(RetryEmailNotification retryEmailNotification) {

        // TODO: access MongoDB -> RetryRepository#save

    }

    @Override
    public void dropRetry(RetryEmailNotification retryEmailNotification) {

        // TODO: access MongoDB -> RetryRepository#delete

    }

    @Override
    public List<RetryEmailNotification> findAllRetries() {

        // TODO: access MongoDB -> RetryRepository#findAll

        return null;
    }
}
