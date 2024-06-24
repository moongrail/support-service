package com.example.support.subscriber;

import com.example.annotation.Subscriber;
import com.example.support.model.SupportPhrase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SupportPhraseSubscriber {
    @Subscriber
    public void subscribe(SupportPhrase supportPhrase) {
        log.info("Input message: {}", supportPhrase.getPhrase());
    }
}
