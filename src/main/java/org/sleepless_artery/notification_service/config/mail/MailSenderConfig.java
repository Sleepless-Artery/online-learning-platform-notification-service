package org.sleepless_artery.notification_service.config.mail;

import lombok.RequiredArgsConstructor;
import org.sleepless_artery.notification_service.config.mail.properties.MailSenderConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
@RequiredArgsConstructor
public class MailSenderConfig {

    private final MailSenderConfigProperties mailSenderConfigProperties;


    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailSenderConfigProperties.getHost());
        mailSender.setPort(mailSenderConfigProperties.getPort());
        mailSender.setUsername(mailSenderConfigProperties.getUsername());
        mailSender.setPassword(mailSenderConfigProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailSenderConfigProperties.getProtocol());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.connectiontimeout", mailSenderConfigProperties.getConnectionTimeout());
        props.put("mail.smtp.timeout", mailSenderConfigProperties.getTimeout());
        props.put("mail.smtp.writetimeout", mailSenderConfigProperties.getWriteTimeout());

        return mailSender;
    }
}
