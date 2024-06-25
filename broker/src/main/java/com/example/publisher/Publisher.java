package com.example.publisher;

import com.example.broker.InMemoryBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Publisher<T>  {
    private  final InMemoryBroker inMemoryBroker;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void publish(T object){
        inMemoryBroker.publish(objectMapper.writeValueAsString(object));
    }

    @SneakyThrows
    public String take(T object){
        return inMemoryBroker.take();
    }
}
