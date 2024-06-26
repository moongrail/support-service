package com.example.support.config;

import com.example.broker.InMemoryBroker;
import com.example.publisher.Publisher;
import com.example.support.dto.SupportPhraseRequest;
import com.example.support.service.impl.BrokerSendingSupportServiceImpl;
import com.example.support.service.SupportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(prefix = "inmemory-broker", name = "enabled", havingValue = "true")
public class NotifyingSupportServiceConfiguration {
    @Bean
    public Publisher<SupportPhraseRequest> publisher(InMemoryBroker inMemoryBroker, ObjectMapper objectMapper) {
        return new Publisher<>(inMemoryBroker, objectMapper);
    }

    @Bean
    @Primary
    public SupportService supportService(Publisher<SupportPhraseRequest> publisher){
        return new BrokerSendingSupportServiceImpl(publisher);
    }
}
