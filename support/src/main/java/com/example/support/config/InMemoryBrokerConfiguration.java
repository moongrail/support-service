package com.example.support.config;

import com.example.bpp.SubscriberBeanPostProcessor;
import com.example.broker.InMemoryBroker;
import com.example.publisher.Publisher;
import com.example.support.dto.SupportPhraseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryBrokerConfiguration {
    @Bean
    public BeanPostProcessor subscriberBeanPostProcessor(){
        return new SubscriberBeanPostProcessor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public InMemoryBroker inMemoryBroker() {
        return new InMemoryBroker();
    }

    @Bean
    public SubscriberBeanPostProcessor.SubscriberContainer subscriberContainer() {
        return new SubscriberBeanPostProcessor.SubscriberContainer(inMemoryBroker(), objectMapper());
    }

    @Bean
    public Publisher<SupportPhraseRequest> publisher(InMemoryBroker inMemoryBroker, ObjectMapper objectMapper) {
        return new Publisher<>(inMemoryBroker, objectMapper);
    }
}
