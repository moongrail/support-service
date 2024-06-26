package com.example.support.repositories;

import com.example.support.model.SupportPhrase;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SupportPhraseRepository {
    private final AtomicLong id = new AtomicLong(0);
    private final Set<SupportPhrase> phraseSet = ConcurrentHashMap.newKeySet();

    public void addPhrase(SupportPhrase supportPhrase) {
            supportPhrase.setId(id.incrementAndGet());
            phraseSet.add(supportPhrase);
    }

    public SupportPhrase getPhrase() {
        return phraseSet.stream()
                .findFirst()
                .orElseThrow();
    }

    public boolean isNotExist(SupportPhrase supportPhrase){
        return !phraseSet.contains(supportPhrase);
    }
}
