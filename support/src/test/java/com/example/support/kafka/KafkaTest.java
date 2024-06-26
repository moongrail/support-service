package com.example.support.kafka;

import com.example.support.dto.SupportPhraseRequest;
import com.example.support.integration.config.SupportPhraseKafkaSendingConfiguration.SupportPhraseKafkaSendingConfigurationProperties;
import com.example.support.model.SupportPhrase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@KafkaIT
class KafkaTest {
    @Autowired
    private Consumer<String, SupportPhrase> consumer;
    @Autowired
    private KafkaTemplate<String, SupportPhrase> kafkaTemplate;
    @Autowired
    private SupportPhraseKafkaSendingConfigurationProperties properties;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    @SneakyThrows
    void testKafkaConsumer(){
        consumer.subscribe(List.of(properties.topic()));
        kafkaTemplate.send(properties.topic(), new SupportPhrase(1L,"Support Phrase")).get();
        StreamSupport.stream(consumer.poll(Duration.ofSeconds(3))
                .spliterator(),false)
                .forEach(System.out::println);
    }

    @Test
    @SneakyThrows
    void testPostKafka(){
        SupportPhraseRequest request = SupportPhraseRequest.builder()
                .phrase("Hello Kafka, be careful")
                .build();
        consumer.subscribe(List.of(properties.topic()));

        mockMvc.perform(post("/support")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        Assertions.assertTrue(StreamSupport.stream(consumer.poll(Duration.ofSeconds(3))
                        .spliterator(),false)
                .map(ConsumerRecord::value)
                .collect(Collectors.toUnmodifiableSet())
                .contains(new SupportPhrase(1L, request.phrase())));
    }
}
