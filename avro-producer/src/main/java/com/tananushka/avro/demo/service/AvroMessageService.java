package com.tananushka.avro.demo.service;

import com.tananushka.avro.demo.AvroMessage;
import com.tananushka.avro.demo.dto.AvroRequest;
import com.tananushka.avro.demo.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AvroMessageService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final KafkaProducer kafkaProducer;

    public void sendMessage(AvroRequest avroRequest) {
        AvroMessage avroMessage = buildAvroMessage(avroRequest);
        kafkaProducer.sendMessage(avroMessage);
    }

    private AvroMessage buildAvroMessage(AvroRequest avroRequest) {
        return AvroMessage.newBuilder()
                .setId(avroRequest.id())
                .setContent(avroRequest.content())
                .setTimestamp(getFormattedTimestamp())
                .build();
    }

    private String getFormattedTimestamp() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
