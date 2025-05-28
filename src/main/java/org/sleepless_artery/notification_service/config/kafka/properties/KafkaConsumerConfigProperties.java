package org.sleepless_artery.notification_service.config.kafka.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Configuration
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Getter @Setter
@Validated
public class KafkaConsumerConfigProperties {

    @NotBlank(message = "Bootstrap servers must be defined")
    private String bootstrapServers;

    @NotBlank(message = "Group ID must be defined")
    private String groupId;

    private final String keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";

    private final String valueDeserializer = "org.springframework.kafka.support.serializer.JsonDeserializer";

    @Pattern(regexp = "earliest|latest|none", message = "Auto offset reset must be one of: earliest, latest, none")
    private final String autoOffsetReset = "earliest";

    private final Boolean enableAutoCommit = false;

    @NotNull(message = "Trusted packages must be defined")
    private String trustedPackages;

    @NotNull(message = "Number of partitions must be defined")
    private Integer concurrency;
}