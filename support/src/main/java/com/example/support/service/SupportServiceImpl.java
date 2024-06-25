package com.example.support.service;

import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SupportServiceImpl implements SupportService {
    @Override
    public void addPhrase(SupportPhraseRequest supportPhraseRequest) {
        log.info("Add Phrase in void: {}", supportPhraseRequest.phrase());
    }

    @Override
    public SupportPhraseResponse getRandomPhrase() {
        log.info("Get OI");
        return SupportPhraseResponse.builder()
                .phrase("OI")
                .build();
    }
}
