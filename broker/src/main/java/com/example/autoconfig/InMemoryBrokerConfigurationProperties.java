package com.example.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("inmemory-broker")
public record InMemoryBrokerConfigurationProperties(
        boolean enabled
) {
}
