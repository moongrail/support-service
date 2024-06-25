package com.example.autoconfig;

import com.example.bpp.SubscriberBeanPostProcessor;
import com.example.broker.InMemoryBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(InMemoryBrokerConfigurationProperties.class)
@ConditionalOnProperty(prefix = "inmemory-broker", name = "enabled", havingValue = "true")
public class InMemoryBrokerAutoConfiguration {
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
}
