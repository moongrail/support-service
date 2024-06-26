package com.example.support.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "application.support-phrase.kafka-sending", name = "enabled", havingValue = "false")
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class AppConfig {
}
