package com.example.bpp;

import com.example.annotation.Subscriber;
import com.example.broker.InMemoryBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriberBeanPostProcessor implements BeanPostProcessor {
    private static final Map<Method, Object> METHOD_TO_BEAN = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods())
                .filter(m -> m.isAnnotationPresent(Subscriber.class))
                .forEach(m -> METHOD_TO_BEAN.put(m, bean));

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @RequiredArgsConstructor
    public static class SubscriberContainer {
        private final InMemoryBroker inMemoryBroker;
        private final ObjectMapper objectMapper;
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        @PostConstruct
        private void init() {
            METHOD_TO_BEAN.entrySet().forEach(e -> executorService.submit(() -> {
                        while (true) {
                            final var message = inMemoryBroker.take();
                            e.getKey()
                                    .invoke(e.getValue(),
                                            objectMapper.readValue(message, e.getKey().getParameterTypes()[0]));
                        }
                    })
            );
        }
    }
}
