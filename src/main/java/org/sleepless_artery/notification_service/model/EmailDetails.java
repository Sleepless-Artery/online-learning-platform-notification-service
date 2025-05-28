package org.sleepless_artery.notification_service.model;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class EmailDetails {

    private String recipient;
    private String subject;
    private String body;
}
