package com.example.support.service.impl;

import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;
import com.example.support.model.SupportPhrase;
import com.example.support.repositories.SupportPhraseRepository;
import com.example.support.service.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportPhraseRepository phraseRepository;

    @Override
    public void addPhrase(SupportPhraseRequest supportPhraseRequest) {
        log.info("Add Phrase in void: {}", supportPhraseRequest.phrase());
        phraseRepository.addPhrase(SupportPhrase.builder()
                .phrase(supportPhraseRequest.phrase())
                .build());
    }

    @Override
    public SupportPhraseResponse getRandomPhrase() {
        SupportPhrase phrase = phraseRepository.getPhrase();
        log.info("Get phrase: {}", phrase);
        return SupportPhraseResponse.builder()
                .phrase(phrase.getPhrase())
                .build();
    }
}
