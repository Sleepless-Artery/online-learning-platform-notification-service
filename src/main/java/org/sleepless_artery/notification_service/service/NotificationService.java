package org.sleepless_artery.notification_service.service;

import org.sleepless_artery.notification_service.model.EmailDetails;


public interface NotificationService {

    void sendMail(EmailDetails emailDetails);
}
