package com.example.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("inmemory-broker")
public record InMemoryBrokerConfigurationProperties(
        boolean enabled
) {
}
