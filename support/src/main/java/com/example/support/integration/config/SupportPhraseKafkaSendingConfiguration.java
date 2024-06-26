package com.example.support.integration.config;

import com.example.support.integration.KafkaNotifyingSupportServiceImpl;
import com.example.support.model.SupportPhrase;
import com.example.support.repositories.SupportPhraseRepository;
import com.example.support.service.SupportService;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;

import static com.example.support.integration.config.SupportPhraseKafkaSendingConfiguration.SupportPhraseKafkaSendingConfigurationProperties;

@Configuration
@ConditionalOnProperty(prefix = "application.support-phrase.kafka-sending", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(SupportPhraseKafkaSendingConfigurationProperties.class)
public class SupportPhraseKafkaSendingConfiguration {
    @Bean
    @Primary
    public SupportService supportService(
            SupportPhraseKafkaSendingConfigurationProperties properties,
            SupportPhraseRepository repository,
            KafkaTemplate<String, SupportPhrase> phraseKafkaTemplate
    ) {
        return new KafkaNotifyingSupportServiceImpl(properties, repository, phraseKafkaTemplate);
    }

    @Bean
    public SupportPhraseKafkaListener supportPhraseKafkaListener(
            SupportPhraseRepository repository
    ){
        return new SupportPhraseKafkaListener(repository);
    }

    @Validated
    @ConfigurationProperties(prefix = "application.support-phrase.kafka-sending")
    public record SupportPhraseKafkaSendingConfigurationProperties(
            String topic,
            @NotNull
            Boolean enabled
    ) {
    }
}
