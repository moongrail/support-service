package com.example.support.integration;

import com.example.support.integration.config.SupportPhraseKafkaSendingConfiguration.SupportPhraseKafkaSendingConfigurationProperties;
import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;
import com.example.support.model.SupportPhrase;
import com.example.support.repositories.SupportPhraseRepository;
import com.example.support.service.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
public class KafkaNotifyingSupportServiceImpl implements SupportService {
    private final SupportPhraseKafkaSendingConfigurationProperties properties;
    private final SupportPhraseRepository phraseRepository;
    private final KafkaTemplate<String, SupportPhrase> phraseKafkaTemplate;

    @Override
    public SupportPhraseResponse getRandomPhrase() {
        return null;
    }

    @Override
    public void addPhrase(SupportPhraseRequest supportPhraseRequest) {
        SupportPhrase supportPhrase = SupportPhrase.builder()
                .phrase(supportPhraseRequest.phrase())
                .build();

        if (phraseRepository.isNotExist(supportPhrase)){
            log.info("Add phrase: {}", supportPhrase);
            phraseKafkaTemplate.send(properties.topic(), supportPhrase);
            phraseRepository.addPhrase(supportPhrase);
        }else {
            log.info("Phrase already exist: {}", supportPhrase);
        }
    }
}
