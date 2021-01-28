package com.mhp.coding.challenges.retry.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class RetryEmailNotification {

    private EmailNotification emailNotification;

    private int retryCount;

}
