package com.example.broker;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class InMemoryBroker {
    private final BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>();

    public void publish(String message) {
        messageQueue.offer(message);
    }

    @SneakyThrows
    public String take() {
        return messageQueue.take();
    }
}
