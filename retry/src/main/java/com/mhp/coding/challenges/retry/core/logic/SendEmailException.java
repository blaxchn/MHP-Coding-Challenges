package com.mhp.coding.challenges.retry.core.logic;

import com.mhp.coding.challenges.retry.core.entities.EmailNotification;

public class SendEmailException extends RuntimeException {

    public SendEmailException(EmailNotification emailNotification) {
        super(String.format("Failed to send email to recipient: %s", emailNotification.getRecipient()));
    }

}
