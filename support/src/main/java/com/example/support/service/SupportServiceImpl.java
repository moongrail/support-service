package com.example.support.service;

import com.example.support.dto.SupportPhraseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService{
    @Override
    public SupportPhraseResponse getRandomPhrase() {
        return SupportPhraseResponse.builder()
                .phrase("OI")
                .build();
    }
}
