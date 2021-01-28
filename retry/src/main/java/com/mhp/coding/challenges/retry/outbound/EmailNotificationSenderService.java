package com.mhp.coding.challenges.retry.outbound;

import com.mhp.coding.challenges.retry.core.entities.EmailNotification;
import com.mhp.coding.challenges.retry.core.logic.SendEmailException;
import com.mhp.coding.challenges.retry.core.outbound.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Service
@Validated
public class EmailNotificationSenderService implements NotificationSender {

    private static final String SENDER_ADDRESS = "info@mhp.com";

    private final JavaMailSender mailSender;

    public EmailNotificationSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(@Valid @NotNull EmailNotification emailNotification) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(SENDER_ADDRESS);
            mailMessage.setTo(emailNotification.getRecipient());
            mailMessage.setSubject(emailNotification.getSubject());
            mailMessage.setText(emailNotification.getText());
            mailSender.send(mailMessage);
        } catch (Exception e) {
            throw new SendEmailException(emailNotification);
        }
    }
}
