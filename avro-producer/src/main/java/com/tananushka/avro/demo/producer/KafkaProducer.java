package com.tananushka.avro.demo.producer;

import com.tananushka.avro.demo.AvroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, AvroMessage> kafkaTemplate;

    @Value("${spring.kafka.topic-name}")
    private String topic;

    public void sendMessage(AvroMessage avroMessage) {
        String key = avroMessage.getId().toString();

        log.info("Sending message with ID={} to topic={}; message={}", key, topic, avroMessage);

        kafkaTemplate.send(topic, key, avroMessage);
    }
}
