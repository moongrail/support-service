package com.example.support.dto;

import lombok.Builder;

@Builder
public record SupportPhraseResponse(
        String phrase
) {
}
