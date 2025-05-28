package org.sleepless_artery.notification_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sleepless_artery.notification_service.exception.SendingEmailException;
import org.sleepless_artery.notification_service.model.EmailDetails;
import org.sleepless_artery.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public void sendMail(EmailDetails emailDetails) {
        if (emailDetails == null || emailDetails.getRecipient() == null) {
            throw new IllegalArgumentException("Email details must not be null");
        }

        log.info("Sending email notification to {}", emailDetails.getRecipient());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(emailDetails.getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getBody());
            mailSender.send(message);
            log.info("Email sent successfully to {}", emailDetails.getRecipient());
        } catch (MailException e) {
            log.error("Error sending email to {}: {}", emailDetails.getRecipient(), e.getMessage());
            throw new SendingEmailException("Error sending message: " + e.getMessage());
        }
    }
}
