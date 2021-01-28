package com.mhp.coding.challenges.retry.core.outbound;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import org.springframework.stereotype.Service;

@Service
public interface RetryNotificationSaver {

    void saveRetry(RetryEmailNotification retryEmailNotification);
    void dropRetry(RetryEmailNotification retryEmailNotification);

}
