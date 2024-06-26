package com.example.support.kafka;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {
        TestKafkaConfiguration.class
})
@EmbeddedKafka(topics = "${application.support-phrase.kafka-sending.topic}", partitions = 1)
@AutoConfigureMockMvc
public @interface KafkaIT {
}
