package com.mhp.coding.challenges.retry.core.inbound;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;

public interface RetryNotificationHandler {

    void retryEmailNotification(RetryEmailNotification retryEmailNotification);
}
