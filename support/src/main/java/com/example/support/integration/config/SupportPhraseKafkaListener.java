package com.example.support.integration.config;

import com.example.support.model.SupportPhrase;
import com.example.support.repositories.SupportPhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
public class SupportPhraseKafkaListener {
    private final SupportPhraseRepository phraseRepository;

    @KafkaListener(
            topics = "${application.support-phrase.kafka-sending.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(SupportPhrase supportPhrase){
        phraseRepository.addPhrase(supportPhrase);
    }
}
