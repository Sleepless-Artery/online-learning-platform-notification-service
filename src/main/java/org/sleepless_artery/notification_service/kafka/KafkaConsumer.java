package org.sleepless_artery.notification_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sleepless_artery.notification_service.model.EmailDetails;
import org.sleepless_artery.notification_service.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NotificationService notificationService;

    private final String CONFIRMATION_CODE = "Your confirmation code: ";


    @KafkaListener(topics = "auth.users.check-email", groupId = "notification-service")
    public void listenCheckEmailEvent(String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        processEmailEvent(key, "Registration confirmation", CONFIRMATION_CODE + message);
    }

    @KafkaListener(topics = "auth.users.change-email-address", groupId = "notification-service")
    public void listenChangeEmailAddressEvent(String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        processEmailEvent(key, "Email confirmation", CONFIRMATION_CODE + message);
    }

    @KafkaListener(topics = "auth.users.reset-password", groupId = "notification-service")
    public void listenResetPasswordEvent(String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        processEmailEvent(key, "Reset password confirmation", CONFIRMATION_CODE + message);
    }


    private void processEmailEvent(String recipient, String subject, String body) {
        log.info("Received message to {} with subject '{}'", recipient, subject);
        notificationService.sendMail(
                EmailDetails.builder()
                        .recipient(recipient)
                        .subject(subject)
                        .body(body)
                        .build()
        );
    }
}