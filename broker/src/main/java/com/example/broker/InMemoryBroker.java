package com.example.broker;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class InMemoryBroker {
    private final BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>();

    public void publish(String message) {
        messageQueue.offer(message);
    }

    @SneakyThrows
    public String subscribe() {
        return messageQueue.take();
    }
}
