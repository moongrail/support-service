package com.example.support.kafka;

import com.example.support.integration.config.SupportPhraseKafkaSendingConfiguration.SupportPhraseKafkaSendingConfigurationProperties;
import com.example.support.model.SupportPhrase;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.List;

@TestConfiguration
public class TestKafkaConfiguration {
    @Bean
    public Consumer<String, SupportPhrase> consumer(
            ConsumerFactory<?, ?> consumerFactory,
            SupportPhraseKafkaSendingConfigurationProperties properties
    ) {
        Consumer<String, SupportPhrase> consumer = (Consumer<String, SupportPhrase>) consumerFactory.createConsumer();
        consumer.subscribe(List.of(properties.topic()));
        return consumer;
    }
}
