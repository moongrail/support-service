package com.example.support.service;

import com.example.publisher.Publisher;
import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class BrokerSendingSupportServiceImpl implements SupportService {
    private final Publisher<SupportPhraseRequest> requestPublisher;

    @Override
    public SupportPhraseResponse getRandomPhrase() {
        return null;
    }

    @Override
    public void addPhrase(SupportPhraseRequest supportPhraseRequest) {
        log.info("Add Phrase in broker: {}", supportPhraseRequest.phrase());
        requestPublisher.publish(supportPhraseRequest);
    }
}
