package com.example.support.service;

import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;

public interface SupportService {
    SupportPhraseResponse getRandomPhrase();
    void addPhrase(SupportPhraseRequest supportPhraseRequest);
}
